package com.initium.assignment.kmp.domain.base

import com.initium.assignment.kmp.domain.repository.RemoteRepoManager
import com.initium.assignment.kmp.domain.repository.remote.contracts.IBaseRepo
import com.initium.assignment.kmp.domain.repository.remote.contracts.IUserRemoteRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class DomainBase: CoroutineScope {
    companion object {
        inline fun <reified T : IBaseRepo> remoteRepo() =
            RemoteRepoManager.instance.getInstance<T>(T::class)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    protected val userRemoteRepo by lazy {
        remoteRepo<IUserRemoteRepo>()
    }

    protected fun startCoroutineNewScope(
        dispatcher: CoroutineDispatcher,
        instance: Any? = null,
        onError: (Throwable) -> Unit = {},
        action: suspend () -> Unit
    ): Job {
        return CoroutineScope(dispatcher).launch {
            runCatching { action.invoke() }
                .onFailure(onError)
        }
    }
}