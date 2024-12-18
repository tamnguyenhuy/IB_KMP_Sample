package com.initium.assignment.data.remote.serverapi.infrastructure

internal sealed class Response<T> {
    abstract val error: ServerException?

    /**
     * Success response with data
     */
    internal data class Success<T>(
        val data: Any,
        override val error: ServerException? = null,
    ) : Response<T>()

    /**
     * Client error response
     */
    internal data class ClientError<T>(
        val body: Any? = null,
        override val error: ServerException,
    ) : Response<T>()

    /**
     * Server error response
     */
    internal data class ServerError<T>(
        val message: String? = null,
        val body: Any? = null,
        override val error: ServerException,
    ) : Response<T>()
}