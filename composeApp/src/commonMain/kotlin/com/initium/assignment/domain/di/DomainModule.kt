
package com.initium.assignment.domain.di

import com.initium.assignment.domain.services.UserService
import org.koin.dsl.module

val domainModules = module {
    factory { UserService(get(), get()) }
}