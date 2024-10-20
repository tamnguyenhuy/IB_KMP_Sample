package com.initium.assignment.domain.model

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual typealias ApplicationContext = Application

actual fun Scope.platformContext(): ApplicationContext {
    return androidContext() as Application
}