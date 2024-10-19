package com.initium.assignment.kmp.domain.repository.remote.contracts

import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.User
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.UserDetail

interface IUserRemoteRepo: IBaseRepo {
    suspend fun fetchUser(itemPerPage: Int, since: Int): Array<User>?

    suspend fun fetchUserDetail(userName: String): UserDetail?
}