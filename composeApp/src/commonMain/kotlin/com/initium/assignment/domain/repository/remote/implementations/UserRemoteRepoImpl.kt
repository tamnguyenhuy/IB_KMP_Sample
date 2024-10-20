package com.initium.assignment.domain.repository.remote.implementations

import co.touchlab.kermit.Logger
import com.initium.assignment.domain.repository.remote.RemoteRepoBase
import com.initium.assignment.domain.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.domain.repository.remote.serverapi.apis.UserApi
import com.initium.assignment.domain.repository.remote.serverapi.infrastructure.ServerException
import com.initium.assignment.domain.repository.remote.serverapi.models.User

class UserRemoteRepoImpl private constructor() : RemoteRepoBase(), IUserRemoteRepo {
    companion object {
        val TAG = UserRemoteRepoImpl::class.simpleName ?: ""
        val instance by lazy {
            UserRemoteRepoImpl()
        }
    }

    // User API instance
    private val userApi by lazy {
        UserApi(BASE_URL)
    }

    override suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User>? {
        return doRequest { auth ->
            userApi.fetchUser(itemPerPage = itemPerPage, since = since, auth = auth)
        }
    }

    override suspend fun fetchUserDetail(userName: String): User? {
        try {
            return doRequest { auth ->
                userApi.fetchUserDetail(userName = userName, auth = auth)
            }
        } catch (ex: Exception) {
            if (ex is ServerException) {
                Logger.d(tag = TAG, messageString = ex.message)
                return null
            }
        }
        return null
    }
}