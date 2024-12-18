package com.initium.assignment.data.remote.serverapi.infrastructure

import io.ktor.client.HttpClient

expect fun platformHttpClient(timeout: Long = 60_000L): HttpClient