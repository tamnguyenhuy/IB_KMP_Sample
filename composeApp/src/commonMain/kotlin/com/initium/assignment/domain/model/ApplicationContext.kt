package com.initium.assignment.domain.model

import org.koin.core.scope.Scope

expect class ApplicationContext

expect fun Scope.platformContext(): ApplicationContext
