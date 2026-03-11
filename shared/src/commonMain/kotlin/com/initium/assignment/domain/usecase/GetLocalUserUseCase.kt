package com.initium.assignment.domain.usecase

import com.initium.assignment.domain.model.User
import com.initium.assignment.domain.repository.IUserLocalRepo

class GetLocalUserUseCase(
    private val userLocalRepo: IUserLocalRepo
) {
    /**
     * Get user by username from local db.
     *
     * @param userName The username of the user.
     *
     * @return User in success case, null otherwise
     */
    operator fun invoke(userName: String): User? {
        return userLocalRepo.getUser(userName = userName)
    }
}
