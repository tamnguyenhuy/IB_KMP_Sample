
package com.initium.assignment.data.di

import com.initium.assignment.data.local.di.dataLocalModule
import com.initium.assignment.data.remote.di.dataRemoteModule
import org.koin.dsl.module

val dataModules = module {
    includes(
        dataLocalModule,
        dataRemoteModule
    )
}