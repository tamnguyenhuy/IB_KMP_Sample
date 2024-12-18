package com.initium.assignment.data.remote.apis.contracts

import com.initium.assignment.data.remote.models.UserDto

interface IUserApi {

    /**
     * Method to fetch user list.
     *
     * @param itemPerPage The number of items per page.
     * @param since The id of the user to start fetching from.
     * @param auth Map of the header parameters.
     *
     * @return array of User
     */
    suspend fun fetchUser(itemPerPage: Int, since: Int, auth: Map<String, String>): Array<UserDto>

    /**
     * Method to fetch user detail.
     *
     * @param userName The username of the user.
     * @param auth Map of the header parameters.
     *
     * @return User in detail
     */
    suspend fun fetchUserDetail(userName: String, auth: Map<String, String>): UserDto
}