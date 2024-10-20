package com.initium.assignment.domain.repository.remote

import com.initium.assignment.domain.repository.IBaseRepoManager
import com.initium.assignment.domain.repository.remote.contracts.IBaseRepo
import com.initium.assignment.domain.repository.remote.contracts.IUserRemoteRepo
import com.initium.assignment.domain.repository.remote.implementations.UserRemoteRepoImpl

class RemoteRepoManager: IBaseRepoManager<IBaseRepo> {
    companion object {
        var instance = RemoteRepoManager()

        fun setEngine(engine: RemoteRepoManager) {
            instance = engine
        }
    }

    /**
     * Method to get the instance of the contract.
     *
     * @param contract The contract.
     *
     * @return The instance of the contract
     * @throws IllegalArgumentException If the contract is unknown
     */
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