package com.initium.assignment.kmp.domain.repository.remote

import com.initium.assignment.kmp.domain.repository.remote.contracts.IBaseRepo

interface IBaseRepoManager<T> where T : IBaseRepo {
    fun <T> getInstance(contract: Any): T
}
