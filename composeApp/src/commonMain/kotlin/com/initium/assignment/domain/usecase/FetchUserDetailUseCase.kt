package com.initium.assignment.domain.usecase

import com.initium.assignment.domain.model.User
import com.initium.assignment.domain.repository.IUserLocalRepo
import com.initium.assignment.domain.repository.IUserRemoteRepo

class FetchUserDetailUseCase(
    private val userRemoteRepo: IUserRemoteRepo,
    private val userLocalRepo: IUserLocalRepo
) {
    /**
     * Fetch user detail from local db or remote server.
     *
     * @param userName The username of the user.
     *
     * @return User in detail in success case, default User otherwise
     */
    suspend operator fun invoke(userName: String): User {
        val userLocal = userLocalRepo.getUser(userName = userName)
        if (userLocal != null && userLocal.isInDetail) {
            return userLocal
        }

        return userRemoteRepo.fetchUserDetail(userName)?.also {
            userLocalRepo.saveUser(it, true)
        } ?: userLocal ?: User()
    }
}
