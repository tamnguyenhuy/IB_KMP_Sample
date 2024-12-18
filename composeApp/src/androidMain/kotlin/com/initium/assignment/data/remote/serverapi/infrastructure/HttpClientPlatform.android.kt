package com.initium.assignment.data.remote.serverapi.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

actual fun platformHttpClient(timeout: Long): HttpClient {
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