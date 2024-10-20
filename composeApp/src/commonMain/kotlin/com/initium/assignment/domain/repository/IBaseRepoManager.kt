package com.initium.assignment.domain.repository

import com.initium.assignment.domain.repository.remote.contracts.IBaseRepo

interface IBaseRepoManager<T> where T : IBaseRepo {
    fun <T> getInstance(contract: Any): T
}
