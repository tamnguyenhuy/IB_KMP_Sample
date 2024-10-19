package com.initium.assignment.kmp.domain.repository.remote

open class RemoteRepoBase {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    protected suspend fun <R> doRequest(
        request: suspend () -> R?
    ): R? {
        return try {
            request()
        } catch (e: Exception) {
            throw e
        }
    }
}