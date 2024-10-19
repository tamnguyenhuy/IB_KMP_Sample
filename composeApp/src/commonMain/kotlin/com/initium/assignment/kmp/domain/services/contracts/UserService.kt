package com.initium.assignment.kmp.domain.services.contracts

import com.initium.assignment.kmp.domain.base.DomainBase
import com.initium.assignment.kmp.domain.model.ApplicationContext
import com.initium.assignment.kmp.domain.model.ListDataStruct
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.User
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.UserDetail

class UserService private constructor(): DomainBase() {
    companion object {
        val instance by lazy {
            UserService()
        }
    }

    fun initialize(context: ApplicationContext) {

    }

    suspend fun fetchUser(itemPerPage: Int, since: Int): ListDataStruct<User> {
        val result = userRemoteRepo.fetchUser(itemPerPage, since) ?: emptyArray()
        return ListDataStruct(
            dataCapacity = Long.MAX_VALUE,
            dataList = result.toList(),
            itemPerPage = itemPerPage
        )
    }


    suspend fun fetchUserDetail(userName: String): UserDetail {
        return userRemoteRepo.fetchUserDetail(userName) ?: UserDetail()
    }
}