package com.initium.assignment.domain.model

import org.koin.core.scope.Scope

actual typealias ApplicationContext = String

actual fun Scope.platformContext(): ApplicationContext {
    return "Temp"
}