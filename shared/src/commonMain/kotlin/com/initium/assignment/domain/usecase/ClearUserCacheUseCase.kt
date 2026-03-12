package com.initium.assignment.domain.usecase

import com.initium.assignment.domain.repository.IUserLocalRepo

class ClearUserCacheUseCase(
    private val userLocalRepo: IUserLocalRepo
) {
    /**
     * Remove all users from local db.
     */
    operator fun invoke() {
        userLocalRepo.removeAllUsers()
    }
}
