package com.initium.assignment.di

import com.initium.assignment.domain.model.platformContext
import com.initium.assignment.ui.app.user.mvi.UserViewModel
import com.initium.assignment.ui.app.userdetail.mvi.UserDetailViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
        )
    }

val viewModelModule = module {
    factory { platformContext() }
    single { UserViewModel(get()) }
    single { UserDetailViewModel() }
}
