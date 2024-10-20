package com.initium.assignment.domain.repository.remote

open class RemoteRepoBase {
    companion object {
        const val BASE_URL = "https://api.github.com"
        const val GITHUB_APIKEY = "" // Replace with your own GitHub API key to increase rate limit
        const val AUTHENTICATION_KEY = "Authorization"
    }

    // Do request with authentication
    protected suspend fun <R> doRequest(
        request: suspend (Map<String, String>) -> R?
    ): R? {
        return try {
            request(mapOf(AUTHENTICATION_KEY to GITHUB_APIKEY))
        } catch (e: Exception) {
            throw e
        }
    }
}