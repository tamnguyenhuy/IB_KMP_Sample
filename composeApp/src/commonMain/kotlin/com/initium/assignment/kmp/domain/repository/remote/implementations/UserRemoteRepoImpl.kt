package com.initium.assignment.kmp.domain.repository.remote.implementations

import com.initium.assignment.kmp.domain.repository.remote.RemoteRepoBase
import com.initium.assignment.kmp.domain.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.kmp.domain.repository.remote.serverapi.apis.UserApi
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.User
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.UserDetail

class UserRemoteRepoImpl private constructor() : RemoteRepoBase(), IUserRemoteRepo {
    companion object {
        val instance by lazy {
            UserRemoteRepoImpl()
        }
    }

    private val userApi by lazy {
        UserApi(BASE_URL)
    }

    override suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User>? {
        return doRequest {
             userApi.fetchUser(itemPerPage, since)
        }

    }

    override suspend fun fetchUserDetail(userName: String): UserDetail? {
        return doRequest {
            userApi.fetchUserDetail(userName)
        }
    }
}