package com.initium.assignment.domain.repository.local

import com.initium.assignment.domain.repository.IBaseRepoManager
import com.initium.assignment.domain.repository.local.contracts.IUserLocalRepo
import com.initium.assignment.domain.repository.local.implementations.UserLocalRepoImpl
import com.initium.assignment.domain.repository.remote.contracts.IBaseRepo

class LocalRepoManager : IBaseRepoManager<IBaseRepo> {

    companion object {
        val instance by lazy { LocalRepoManager() }
    }

    override fun <T> getInstance(contract: Any): T {
        return doGetInstance(contract)
    }

    /**
     * Method to get the instance of the contract.
     *
     * @param contract The contract.
     *
     * @return The instance of the contract
     * @throws IllegalArgumentException If the contract is unknown
     */
    private fun <T> doGetInstance(contract: Any): T {
        return when (contract) {
            IUserLocalRepo::class -> {
                UserLocalRepoImpl.instance
            }
            else -> {
                throw IllegalArgumentException("Unknown contract")
            }
        } as T
    }
}