package com.initium.assignment.domain.repository.local.contracts

import com.initium.assignment.domain.repository.remote.contracts.IBaseRepo
import com.initium.assignment.domain.repository.remote.serverapi.models.User

interface IUserLocalRepo: IBaseRepo {

    /**
     * Method to get all users from local db.
     *
     * @return List of User
     */
    fun getAllUsers(): List<User>

    /**
     * Method to get user by id from local db.
     *
     * @param id The id of the user.
     *
     * @return User in success case, null otherwise
     */
    fun getUser(id: Int): User?

    /**
     * Method to get user by username from local db.
     *
     * @param userName The username of the user.
     *
     * @return User in success case, null otherwise
     */
    fun getUser(userName: String): User?

    /**
     * Method to save user to local db.
     *
     * @param user The user to save.
     * @param inDetail The flag to indicate if user is in detail.
     */
    fun saveUser(user: User, inDetail: Boolean = false)

    /**
     * Method to remove all users from local db.
     */
    fun removeAllUsers()
}