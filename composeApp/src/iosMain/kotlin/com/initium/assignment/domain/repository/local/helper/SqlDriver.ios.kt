package com.initium.assignment.domain.repository.local.helper

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.initium.assignment.domain.model.ApplicationContext
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase

actual fun sqlDriverFactory(context: ApplicationContext): SqlDriver {
    return NativeSqliteDriver(MyDatabase.Schema, "MyDatabase.db")
}

actual fun createDatabase(driver: SqlDriver): MyDatabase {
    return MyDatabase(driver)
}

