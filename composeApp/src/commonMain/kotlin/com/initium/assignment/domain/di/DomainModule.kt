
package com.initium.assignment.domain.di

import com.initium.assignment.domain.usecase.ClearUserCacheUseCase
import com.initium.assignment.domain.usecase.FetchUserDetailUseCase
import com.initium.assignment.domain.usecase.FetchUsersUseCase
import com.initium.assignment.domain.usecase.GetLocalUserUseCase
import org.koin.dsl.module

val domainModules = module {
    factory { FetchUsersUseCase(get(), get()) }
    factory { FetchUserDetailUseCase(get(), get()) }
    factory { GetLocalUserUseCase(get()) }
    factory { ClearUserCacheUseCase(get()) }
}