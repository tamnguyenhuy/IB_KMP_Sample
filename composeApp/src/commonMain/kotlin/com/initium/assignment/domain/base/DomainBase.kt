package com.initium.assignment.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class DomainBase: CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    protected fun startCoroutineNewScope(
        dispatcher: CoroutineDispatcher,
        onError: (Throwable) -> Unit = {},
        action: suspend () -> Unit
    ): Job {
        return CoroutineScope(dispatcher).launch {
            runCatching { action.invoke() }
                .onFailure(onError)
        }
    }
}