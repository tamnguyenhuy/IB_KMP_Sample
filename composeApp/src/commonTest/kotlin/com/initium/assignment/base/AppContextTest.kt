package com.initium.assignment.base

import com.initium.assignment.domain.model.ApplicationContext

expect abstract class Base() {
    fun getAppContext() : ApplicationContext
}
