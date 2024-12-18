package com.initium.assignment.data.local.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {
    return AndroidSqliteDriver(
        schema = MyDatabase.Schema,
        context = androidContext(),
        name = "MyDatabase.db"
    )
}