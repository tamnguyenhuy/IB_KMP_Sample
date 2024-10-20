package com.initium.assignment.domain.repository.local.helper

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.initium.assignment.domain.model.ApplicationContext
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase

actual fun sqlDriverFactory(context: ApplicationContext): SqlDriver {
    return AndroidSqliteDriver(
        schema = MyDatabase.Schema,
        context = context as Context,
        name = "MyDatabase.db"
    )
}

actual fun createDatabase(driver: SqlDriver): MyDatabase {
    val database = MyDatabase(
        driver = driver
    )
    return database
}