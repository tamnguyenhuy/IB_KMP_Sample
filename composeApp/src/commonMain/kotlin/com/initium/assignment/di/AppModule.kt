package com.initium.assignment.di

import com.initium.assignment.data.di.dataModules
import com.initium.assignment.domain.di.domainModules
import com.initium.assignment.ui.di.uiModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
    }

val appModule = module {
    includes(dataModules, domainModules, uiModule)
}
