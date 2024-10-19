package com.initium.assignment.kmp.ui.di

import com.initium.assignment.kmp.ui.app.user.mvi.UserViewModel
import com.initium.assignment.kmp.ui.app.userdetail.mvi.UserDetailViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            repositoriesModule,
        )
    }

val viewModelModule = module {
    single { UserViewModel() }
    single { UserDetailViewModel() }
}

val repositoriesModule = module {  }
