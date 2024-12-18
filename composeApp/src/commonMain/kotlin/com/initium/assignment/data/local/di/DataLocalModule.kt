package com.initium.assignment.data.local.di

import com.initium.assignment.data.local.contracts.IUserLocal
import com.initium.assignment.data.local.database.createDatabase
import com.initium.assignment.data.local.database.sqlDriverFactory
import com.initium.assignment.data.local.implementations.UserLocalImpl
import com.initium.assignment.data.repository.local.contracts.IUserLocalRepo
import com.initium.assignment.data.repository.local.implementaions.UserLocalRepoImpl
import org.koin.dsl.module

val dataLocalModule = module {
    factory { sqlDriverFactory() }
    single { createDatabase(get()) }

    single<IUserLocal> { UserLocalImpl(get()) }
    single<IUserLocalRepo> { UserLocalRepoImpl(get()) }
}