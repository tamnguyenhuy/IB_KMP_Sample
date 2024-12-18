package com.initium.assignment.data.remote.di

import com.initium.assignment.data.remote.apis.implementation.UserApiImpl
import com.initium.assignment.data.remote.apis.contracts.IUserApi
import com.initium.assignment.data.remote.serverapi.infrastructure.platformHttpClient
import com.initium.assignment.data.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.data.repository.remote.implementations.UserRemoteRepoImpl
import org.koin.dsl.module

val dataRemoteModule = module {
    single {
        platformHttpClient()
    }

    single<IUserApi> { UserApiImpl(httpClient = get()) }
    single<IUserRemoteRepo> { UserRemoteRepoImpl(get()) }
}
