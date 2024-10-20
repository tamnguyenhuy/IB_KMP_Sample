package com.initium.assignment.base

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.initium.assignment.domain.model.ApplicationContext
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
actual abstract class Base actual constructor() {
    actual fun getAppContext(): ApplicationContext {
        return ApplicationProvider.getApplicationContext()
    }
}