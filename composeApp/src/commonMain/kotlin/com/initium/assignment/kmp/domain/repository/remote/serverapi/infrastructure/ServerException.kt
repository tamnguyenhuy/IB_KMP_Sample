package com.initium.assignment.kmp.domain.repository.remote.serverapi.infrastructure

import com.initium.assignment.kmp.domain.repository.remote.serverapi.enums.ServerErrorCode

data class ServerException(
    val httpCode: Int = 500,
    val errorCode: ServerErrorCode? = null,
    val url: String? = null,
    val requestBody: String? = null
) : Exception() {
    override val message: String
        get() = "Server error occurred with code $httpCode for url $url with request body $requestBody"
}