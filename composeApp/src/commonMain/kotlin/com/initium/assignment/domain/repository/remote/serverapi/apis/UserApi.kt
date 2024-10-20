package com.initium.assignment.domain.repository.remote.serverapi.apis

import com.initium.assignment.domain.repository.remote.serverapi.infrastructure.ApiClient
import com.initium.assignment.domain.repository.remote.serverapi.infrastructure.MultiValueMap
import com.initium.assignment.domain.repository.remote.serverapi.infrastructure.RequestConfig
import com.initium.assignment.domain.repository.remote.serverapi.infrastructure.RequestMethod
import com.initium.assignment.domain.repository.remote.serverapi.models.User

class UserApi(basePath: String): ApiClient(basePath) {

    /**
     * Method to fetch user list.
     *
     * @param itemPerPage The number of items per page.
     * @param since The id of the user to start fetching from.
     * @param auth Map of the header parameters.
     *
     * @return array of User
     */
    suspend fun fetchUser(itemPerPage: Int, since: Int, auth: Map<String, String>): Array<User> {
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

        val response = request<Array<User>>(requestConfig, auth)
        return handleResponse(response)
    }

    /**
     * Method to fetch user detail.
     *
     * @param userName The username of the user.
     * @param auth Map of the header parameters.
     *
     * @return User in detail
     */
    suspend fun fetchUserDetail(userName: String, auth: Map<String, String>): User {
        val localVariableQuery: MultiValueMap = mapOf()

        val contentHeaders: Map<String, String> = mapOf()
        val acceptsHeaders: Map<String, String> = mapOf("Content-Type" to "application/json")

        val localVariableHeaders: MutableMap<String,String> = mutableMapOf()
        localVariableHeaders.putAll(contentHeaders)
        localVariableHeaders.putAll(acceptsHeaders)

        val requestConfig = RequestConfig(
            method = RequestMethod.GET,
            path = "/users/{username}".replace("{"+"username"+"}", userName),
            query = localVariableQuery,
            headers = localVariableHeaders
        )

        val response = request<User>(requestConfig, auth)
        return handleResponse(response)
    }
}