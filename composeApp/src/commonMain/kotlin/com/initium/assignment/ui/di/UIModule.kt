package com.initium.assignment.ui.di

import com.initium.assignment.ui.app.user.mvi.UserViewModel
import com.initium.assignment.ui.app.userdetail.mvi.UserDetailViewModel
import org.koin.dsl.module

val uiModule = module {
    single { UserViewModel(get()) }
    single { UserDetailViewModel(get()) }
}
