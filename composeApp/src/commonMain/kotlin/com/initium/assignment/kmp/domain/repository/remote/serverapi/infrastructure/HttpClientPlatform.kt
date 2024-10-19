package com.initium.assignment.kmp.domain.repository.remote.serverapi.infrastructure

import io.ktor.client.HttpClient

expect class HttpClientPlatform() {
    fun getHttpClient(timeout: Long = 30_000L): HttpClient
}