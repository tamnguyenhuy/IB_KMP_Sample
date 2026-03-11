package com.initium.assignment.domain.usecase

import com.initium.assignment.domain.model.ListDataStruct
import com.initium.assignment.domain.model.User
import com.initium.assignment.domain.repository.IUserLocalRepo
import com.initium.assignment.domain.repository.IUserRemoteRepo

class FetchUsersUseCase(
    private val userRemoteRepo: IUserRemoteRepo,
    private val userLocalRepo: IUserLocalRepo
) {
    // Flag to check if data exists in local db
    private var isHasDbData: Boolean = false

    /**
     * Fetch user list from local db or remote server.
     *
     * @param itemPerPage The number of items per page.
     * @param since The id of the user to start fetching from.
     *
     * @return ListDataStruct of User in success case, empty list otherwise or throw ServerException
     */
    suspend operator fun invoke(itemPerPage: Int, since: Int): ListDataStruct<User> {
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
}
