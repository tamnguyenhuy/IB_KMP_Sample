package com.initium.assignment.base

import com.initium.assignment.domain.model.ApplicationContext

actual abstract class Base actual constructor() {
    actual fun getAppContext(): ApplicationContext {
        return "context"
    }
}