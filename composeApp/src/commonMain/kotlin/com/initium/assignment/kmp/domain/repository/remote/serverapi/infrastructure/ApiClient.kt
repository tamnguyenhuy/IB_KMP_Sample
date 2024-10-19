package com.initium.assignment.kmp.domain.repository.remote.serverapi.infrastructure

import com.initium.assignment.kmp.domain.repository.remote.serverapi.enums.ServerErrorCode
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import sp.bvantur.inspektify.ktor.DataRetentionPolicy
import sp.bvantur.inspektify.ktor.InspektifyKtor
import sp.bvantur.inspektify.ktor.LogLevel
import sp.bvantur.inspektify.ktor.PresentationType

open class ApiClient(val basePath: String) {
    var httpClient = HttpClientPlatform().getHttpClient()
    var isConfig = false

    companion object {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    internal suspend inline fun <reified T : Any?> request(
        requestConfig: RequestConfig,
        bodyData: Any? = null): Response<T> {

        val functionPath = requestConfig.path
        val fullPath = "$basePath$functionPath"

        val requestData = buildRequestData {
            url(fullPath)
            method = requestConfig.method.toKtorMethod()
            headers {
                requestConfig.headers.forEach { (key, value) ->
                    append(key, value)
                }
            }

            requestConfig.query.forEach { (key, values) ->
                values.forEach { value ->
                    parameter(key, value)
                }
            }

            if (bodyData != null) {
                setBody(bodyData)
            }
        }

        if (!isConfig) {
            isConfig = true
            httpClient = withContext(Dispatchers.Main) {
                httpClient.config {
                    install(InspektifyKtor) {
                        presentationType = PresentationType.AutoDetect
                        logLevel = LogLevel.All
                        dataRetentionPolicy = DataRetentionPolicy.DayDuration(1)
                    }
                }
            }
        }

        return httpClient.requestAndCatch<T>(bodyData) {
            request (requestData)
        }
    }

    internal suspend inline fun <reified T> HttpClient.requestAndCatch(
        bodyData: Any?,
        block: HttpClient.() -> HttpResponse
    ): Response<T> {
        val data = block()
        return if (data.status.isSuccess()) {
            Response.Success(
                data = data,
            )
        } else {
            handleException(data, bodyData)
        }
    }

    internal suspend inline fun <reified T> handleException(
        response: HttpResponse,
        bodyData: Any?
    ): Response<T> {
        val statusCode = response.status.value
        val errorBody = response.bodyAsText()
        if (statusCode in 500..599) {
            return when (statusCode) {
                504 -> Response.ServerError(
                    error = ServerException(
                        errorCode = ServerErrorCode.RequestTimeout,
                        httpCode = statusCode
                    ),
                    body = "504 Gateway Time-out"
                )

                else -> Response.ServerError(
                    error = ServerException(
                        errorCode = ServerErrorCode.ServerException,
                        httpCode = statusCode
                    ),
                    body = "Server Error Code: $statusCode"
                )
            }
        } else {
            return when (statusCode) {
                400 -> {
                    Response.ClientError(
                        error = ServerException(
                            httpCode = statusCode,
                            errorCode = ServerErrorCode.BadRequest
                        ), body = errorBody
                    )
                }

                in 401..499 -> Response.ClientError(
                    error = ServerException(
                        httpCode = statusCode,
                        errorCode = ServerErrorCode.Unauthorized
                    ), body = errorBody
                )
                else -> throw ResponseException(response, errorBody)
            }
        }
    }

    internal suspend inline fun <reified T> handleResponse(response: Response<T>): T {
        return when (response) {
            is Response.Success -> {
                when (T::class) {
                    else -> {
                        val jsonStr = if (response.data is HttpResponse) {
                            response.data.bodyAsText()
                        } else {
                            response.data as String
                        }
                        if (T::class == String::class) {
                            jsonStr as T
                        } else {
                            json.decodeFromString(jsonStr)
                        }
                    }
                }
            }

            is Response.ClientError -> throw (response as Response.ClientError<*>).error
            is Response.ServerError -> throw (response as Response.ServerError<*>).error
        }
    }

    private fun buildRequestData(block: HttpRequestBuilder.() -> Unit) =
        HttpRequestBuilder().apply(block)

}