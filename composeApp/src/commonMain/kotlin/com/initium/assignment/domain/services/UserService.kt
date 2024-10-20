package com.initium.assignment.domain.services

import com.initium.assignment.domain.base.DomainBase
import com.initium.assignment.domain.model.ListDataStruct
import com.initium.assignment.domain.repository.remote.serverapi.models.User
import kotlinx.coroutines.Dispatchers

class UserService private constructor(): DomainBase() {
    companion object {
        val instance by lazy {
            UserService()
        }
    }

    // flag to check if data exists in local db
    private var isHasDbData: Boolean = false

    /**
     * Method to remove all users from local db.
     */
    fun removeALlUsers() {
        userLocalRepo.removeAllUsers()
    }

    /**
     * Method to get all users from local db.
     *
     * @return List of User
     */
    fun fetchAllUsers(): List<User> {
        return userLocalRepo.getAllUsers()
    }

    /**
     * Method to get user by id from local db.
     *
     * @param userName The userName of the user.
     *
     * @return User in success case, null otherwise
     */
    fun getUser(userName: String): User? {
        return userLocalRepo.getUser(userName = userName)
    }

    /**
     * Method to get user list from local db or remote server.
     *
     * @param itemPerPage The number of items per page.
     * @param since The id of the user to start fetching from.
     *
     * @return ListDataStruct of User in success case, empty list otherwise or throw ServerException
     */
    suspend fun fetchUser(itemPerPage: Int, since: Int): ListDataStruct<User> {
        // Fetch all users from local db first time
        if (!isHasDbData) {
            userLocalRepo.getAllUsers().let {
                if (it.isNotEmpty()) {
                    // Set flag to true if data exists in local db
                    isHasDbData = true
                    return ListDataStruct(
                        dataCapacity = Long.MAX_VALUE,
                        dataList = it,
                        itemPerPage = itemPerPage
                    )
                }
            }
        }

        // Fetch users from remote server if does not exist in local db
        val result = userRemoteRepo.fetchUser(itemPerPage, since) ?: emptyArray()
        result.forEach {
            userLocalRepo.saveUser(it)
        }

        return ListDataStruct(
            dataCapacity = Long.MAX_VALUE,
            dataList = result.toList(),
            itemPerPage = itemPerPage
        )
    }


    /**
     * Method to get user detail from local db or remote server.
     *
     * @param userName The username of the user.
     *
     * @return User in detail in success case, default User otherwise
     */
    suspend fun fetchUserDetail(userName: String): User {
        val userLocal = userLocalRepo.getUser(userName = userName)
        if (userLocal != null && userLocal.isInDetail) {
            return userLocal
        }

        return userRemoteRepo.fetchUserDetail(userName)?.also {
            userLocalRepo.saveUser(it, true)
        } ?: userLocal ?: User()
    }
}