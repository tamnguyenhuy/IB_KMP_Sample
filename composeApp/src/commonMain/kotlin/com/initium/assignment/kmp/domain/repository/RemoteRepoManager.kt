package com.initium.assignment.kmp.domain.repository

import com.initium.assignment.kmp.domain.repository.remote.contracts.IBaseRepo
import com.initium.assignment.kmp.domain.repository.remote.IBaseRepoManager
import com.initium.assignment.kmp.domain.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.kmp.domain.repository.remote.implementations.UserRemoteRepoImpl

class RemoteRepoManager: IBaseRepoManager<IBaseRepo> {
    companion object {
        var instance = RemoteRepoManager()

        fun setEngine(engine: RemoteRepoManager) {
            instance = engine
        }
    }

    override fun <T> getInstance(contract: Any): T {
        return doGetInstance(contract)
    }

    private fun <T> doGetInstance(contract: Any): T {
        return when (contract) {
            IUserRemoteRepo::class -> {
                UserRemoteRepoImpl.instance
            }
            else -> {
                throw IllegalArgumentException("Unknown contract")
            }
        } as T
    }
}