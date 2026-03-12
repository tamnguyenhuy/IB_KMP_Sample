package com.initium.assignment.data.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {
    return NativeSqliteDriver(MyDatabase.Schema, "MyDatabase.db")
}
