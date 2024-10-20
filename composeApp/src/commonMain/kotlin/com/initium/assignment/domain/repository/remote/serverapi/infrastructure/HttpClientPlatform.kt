package com.initium.assignment.domain.repository.remote.serverapi.infrastructure

import io.ktor.client.HttpClient

expect class HttpClientPlatform() {
    fun getHttpClient(timeout: Long = 60_000L): HttpClient
}