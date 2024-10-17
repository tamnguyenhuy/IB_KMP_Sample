package com.initium.assignment.kmp.domain.serverapi.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

actual class HttpClientPlatform {
    actual fun getHttpClient(timeout: Long): HttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .build()

        val client = HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
            }
        }
        return client
    }
}