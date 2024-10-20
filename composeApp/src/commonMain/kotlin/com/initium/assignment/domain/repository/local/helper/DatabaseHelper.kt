package com.initium.assignment.domain.repository.local.helper

import com.initium.assignment.domain.model.ApplicationContext
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase

class DatabaseHelper private constructor() {
    private var database: MyDatabase? = null

    companion object {
        val instance by lazy { DatabaseHelper() }
    }

    /**
     * Method to initialize the database.
     * Need to call this method before using the database.
     *
     * @param context The application context.
     */
    fun initDatabase(context: ApplicationContext) {
        this.database = database ?: createDatabase(sqlDriverFactory(context))
    }

    /**
     * Method to get the database.
     *
     * @return MyDatabase instance in success case,
     * @throws IllegalStateException if database not initialized
     */
    fun getDatabase(): MyDatabase {
        return database ?: throw IllegalStateException("Database not initialized")
    }
}