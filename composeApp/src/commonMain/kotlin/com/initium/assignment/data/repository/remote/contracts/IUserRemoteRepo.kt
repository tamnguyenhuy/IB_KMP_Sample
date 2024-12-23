package com.initium.assignment.data.repository.remote.contracts

import com.initium.assignment.model.User

interface IUserRemoteRepo {

    /**
     * Method to fetch user list.
     *
     * @param itemPerPage The number of items per page.
     * @param since The id of the user to start fetching from.
     *
     * @return Array of User in success case, null otherwise
     */
    suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User>?

    /**
     * Method to fetch user detail.
     *
     * @param userName The username of the user.
     *
     * @return User in detail in success case, null otherwise
     */
    suspend fun fetchUserDetail(userName: String): User?
}