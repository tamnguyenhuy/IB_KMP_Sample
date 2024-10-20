package com.initium.assignment.domain.repository.remote.serverapi.infrastructure

import io.ktor.http.*

/**
 * Provides enumerated HTTP verbs
 */
enum class RequestMethod {
    GET, DELETE, HEAD, OPTIONS, PATCH, POST, PUT;

    fun toKtorMethod(): HttpMethod {
        return when (this) {
            DELETE -> HttpMethod.Delete
            GET -> HttpMethod.Get
            HEAD -> HttpMethod.Head
            PATCH -> HttpMethod.Patch
            PUT -> HttpMethod.Put
            POST -> HttpMethod.Post
            OPTIONS -> HttpMethod.Options
        }
    }
}