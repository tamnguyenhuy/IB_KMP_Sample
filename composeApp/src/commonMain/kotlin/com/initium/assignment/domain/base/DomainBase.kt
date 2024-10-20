package com.initium.assignment.domain.base

import com.initium.assignment.domain.repository.local.helper.DatabaseHelper
import com.initium.assignment.domain.repository.local.LocalRepoManager
import com.initium.assignment.domain.repository.local.contracts.IUserLocalRepo
import com.initium.assignment.domain.repository.remote.RemoteRepoManager
import com.initium.assignment.domain.repository.remote.contracts.IBaseRepo
import com.initium.assignment.domain.repository.remote.contracts.IUserRemoteRepo
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

        inline fun <reified T : IBaseRepo> localRepo() =
            LocalRepoManager.instance.getInstance<T>(T::class)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    protected val dataHelper by lazy {
        DatabaseHelper.instance
    }

    protected val userRemoteRepo by lazy {
        remoteRepo<IUserRemoteRepo>()
    }

    protected val userLocalRepo by lazy {
        localRepo<IUserLocalRepo>()
    }

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