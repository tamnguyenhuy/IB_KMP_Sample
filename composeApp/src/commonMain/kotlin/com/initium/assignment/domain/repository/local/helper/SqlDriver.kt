package com.initium.assignment.domain.repository.local.helper

import app.cash.sqldelight.db.SqlDriver
import com.initium.assignment.domain.model.ApplicationContext
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase


/**
 * Method to get the sql driver.
 *
 * @param context The application context.
 *
 * @return SqlDriver instance.
 */
expect fun sqlDriverFactory(context: ApplicationContext): SqlDriver

/**
 * Method to create the database.
 *
 * @param driver The sql driver.
 *
 * @return MyDatabase instance.
 */
expect fun createDatabase(driver: SqlDriver): MyDatabase