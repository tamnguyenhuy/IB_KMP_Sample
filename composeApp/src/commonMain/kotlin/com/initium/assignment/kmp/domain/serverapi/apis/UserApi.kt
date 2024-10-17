package com.initium.assignment.kmp.domain.serverapi.apis

import com.initium.assignment.kmp.domain.serverapi.infrastructure.ApiClient
import com.initium.assignment.kmp.domain.serverapi.infrastructure.MultiValueMap
import com.initium.assignment.kmp.domain.serverapi.infrastructure.RequestConfig
import com.initium.assignment.kmp.domain.serverapi.infrastructure.RequestMethod
import com.initium.assignment.kmp.domain.serverapi.models.User
import com.initium.assignment.kmp.domain.serverapi.models.UserDetail

class UserApi(basePath: String): ApiClient(basePath) {

    suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User> {
        val localVariableQuery: MultiValueMap = mapOf(
            "per_page" to listOfNotNull(itemPerPage.toString()),
            "since" to listOfNotNull(since.toString())
        )

        val contentHeaders: Map<String, String> = mapOf()
        val acceptsHeaders: Map<String, String> = mapOf("Content-Type" to "application/json")

        val localVariableHeaders: MutableMap<String,String> = mutableMapOf()
        localVariableHeaders.putAll(contentHeaders)
        localVariableHeaders.putAll(acceptsHeaders)

        val requestConfig = RequestConfig(
            method = RequestMethod.GET,
            path = "/users",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        val response = request<Array<User>>(requestConfig)
        return handleResponse(response)
    }

    suspend fun fetchUserDetail(username: String): UserDetail {
        val localVariableQuery: MultiValueMap = mapOf()

        val contentHeaders: Map<String, String> = mapOf()
        val acceptsHeaders: Map<String, String> = mapOf("Content-Type" to "application/json")

        val localVariableHeaders: MutableMap<String,String> = mutableMapOf()
        localVariableHeaders.putAll(contentHeaders)
        localVariableHeaders.putAll(acceptsHeaders)

        val requestConfig = RequestConfig(
            method = RequestMethod.GET,
            path = "/users/{username}".replace("{"+"username"+"}", username),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        val response = request<UserDetail>(requestConfig)
        return handleResponse(response)
    }
}