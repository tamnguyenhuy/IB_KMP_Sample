package com.initium.assignment.kmp.domain.serverapi.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout

actual class HttpClientPlatform {
    actual fun getHttpClient(timeout: Long): HttpClient {
        return HttpClient(Darwin).apply {
            this.config {
                install(HttpTimeout) {
                    this.connectTimeoutMillis = timeout
                    this.requestTimeoutMillis = timeout
                    this.socketTimeoutMillis = timeout
                }
            }
        }
    }
}