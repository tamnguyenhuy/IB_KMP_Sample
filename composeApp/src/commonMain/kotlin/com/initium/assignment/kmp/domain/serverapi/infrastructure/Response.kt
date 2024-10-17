package com.initium.assignment.kmp.domain.serverapi.infrastructure

internal sealed class Response<T> {
    abstract val error: ServerException?

    internal data class Success<T>(
        val data: Any,
        override val error: ServerException? = null,
    ) : Response<T>()

    internal data class ClientError<T>(
        val body: Any? = null,
        override val error: ServerException,
    ) : Response<T>()

    internal data class ServerError<T>(
        val message: String? = null,
        val body: Any? = null,
        override val error: ServerException,
    ) : Response<T>()
}