package com.initium.assignment.domain.services

import com.initium.assignment.domain.base.DomainBase
import com.initium.assignment.domain.model.ApplicationContext

class InitialService private constructor(): DomainBase() {
    companion object {
        val instance by lazy { InitialService() }
    }

    /**
     * Method to initialize the database.
     *
     * @param context The application context.
     *
     */
    fun initialize(context: ApplicationContext) {
        dataHelper.initDatabase(context)
    }
}