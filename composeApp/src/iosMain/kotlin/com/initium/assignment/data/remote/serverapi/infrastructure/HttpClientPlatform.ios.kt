package com.initium.assignment.data.remote.serverapi.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout

actual fun platformHttpClient(timeout: Long): HttpClient {
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