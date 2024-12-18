package com.initium.assignment.data.remote.apis.implementation

import com.initium.assignment.data.remote.apis.contracts.IUserApi
import com.initium.assignment.data.remote.models.UserDto
import com.initium.assignment.data.remote.serverapi.infrastructure.ApiClient
import com.initium.assignment.data.remote.serverapi.infrastructure.MultiValueMap
import com.initium.assignment.data.remote.serverapi.infrastructure.RequestConfig
import com.initium.assignment.data.remote.serverapi.infrastructure.RequestMethod
import com.initium.assignment.data.repository.remote.RemoteRepoBase.Companion.BASE_URL
import io.ktor.client.HttpClient

class UserApiImpl(
    basePath: String = BASE_URL,
    httpClient: HttpClient
) : ApiClient(basePath, httpClient), IUserApi {

    override suspend fun fetchUser(
        itemPerPage: Int,
        since: Int,
        auth: Map<String, String>
    ): Array<UserDto> {
        val localVariableQuery: MultiValueMap = mapOf(
            "per_page" to listOfNotNull(itemPerPage.toString()),
            "since" to listOfNotNull(since.toString())
        )

        val contentHeaders: Map<String, String> = mapOf()
        val acceptsHeaders: Map<String, String> = mapOf("Content-Type" to "application/json")

        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders.putAll(contentHeaders)
        localVariableHeaders.putAll(acceptsHeaders)

        val requestConfig = RequestConfig(
            method = RequestMethod.GET,
            path = "/users",
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        val response = request<Array<UserDto>>(requestConfig, auth)
        return handleResponse(response)
    }

    override suspend fun fetchUserDetail(userName: String, auth: Map<String, String>): UserDto {
        val localVariableQuery: MultiValueMap = mapOf()

        val contentHeaders: Map<String, String> = mapOf()
        val acceptsHeaders: Map<String, String> = mapOf("Content-Type" to "application/json")

        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders.putAll(contentHeaders)
        localVariableHeaders.putAll(acceptsHeaders)

        val requestConfig = RequestConfig(
            method = RequestMethod.GET,
            path = "/users/{username}".replace("{" + "username" + "}", userName),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        val response = request<UserDto>(requestConfig, auth)
        return handleResponse(response)
    }
}