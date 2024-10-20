package com.initium.assignment.domain.repository.remote.serverapi.infrastructure

data class RequestConfig(
    val method: RequestMethod,
    val path: String,
    val headers: Map<String, String> = mapOf(),
    val query: Map<String, List<String>> = mapOf()
)