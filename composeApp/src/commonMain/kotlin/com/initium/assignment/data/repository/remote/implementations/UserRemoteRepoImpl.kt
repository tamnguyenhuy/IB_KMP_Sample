package com.initium.assignment.data.repository.remote.implementations

import co.touchlab.kermit.Logger
import com.initium.assignment.data.remote.apis.contracts.IUserApi
import com.initium.assignment.data.remote.serverapi.infrastructure.ServerException
import com.initium.assignment.data.repository.remote.RemoteRepoBase
import com.initium.assignment.data.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.model.User

class UserRemoteRepoImpl(private val userApi: IUserApi) : RemoteRepoBase(), IUserRemoteRepo {
    companion object {
        val TAG = UserRemoteRepoImpl::class.simpleName ?: ""
    }

    override suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User>? {
        return doRequest { auth ->
            userApi.fetchUser(itemPerPage = itemPerPage, since = since, auth = auth).map { it.mapToUser() }.toTypedArray()
        }
    }

    override suspend fun fetchUserDetail(userName: String): User? {
        try {
            return doRequest { auth ->
                userApi.fetchUserDetail(userName = userName, auth = auth).mapToUser()
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