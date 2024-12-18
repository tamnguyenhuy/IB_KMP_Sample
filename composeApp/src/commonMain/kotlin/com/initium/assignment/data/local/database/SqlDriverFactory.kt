package com.initium.assignment.data.local.database

import app.cash.sqldelight.db.SqlDriver
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory() : SqlDriver

fun createDatabase(driver: SqlDriver): MyDatabase {
    return MyDatabase(
        driver = driver,
    )
}