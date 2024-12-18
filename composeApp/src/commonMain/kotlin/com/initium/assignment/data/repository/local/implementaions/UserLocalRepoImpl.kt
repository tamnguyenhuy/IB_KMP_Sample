
package com.initium.assignment.data.repository.local.implementaions

import com.initium.assignment.UserDb
import com.initium.assignment.data.local.contracts.IUserLocal
import com.initium.assignment.data.repository.local.contracts.IUserLocalRepo
import com.initium.assignment.model.User

class UserLocalRepoImpl(private val userLocal: IUserLocal): IUserLocalRepo {
    override fun getAllUsers(): List<User> {
        return userLocal.getAllUsers().map { it.toUser() }
    }

    override fun getUser(id: Int): User? {
        return userLocal.getUser(id)?.toUser()
    }

    override fun getUser(userName: String): User? {
        return userLocal.getUser(userName)?.toUser()
    }

    override fun saveUser(user: User, inDetail: Boolean) {
        return userLocal.saveUser(user, inDetail)
    }

    override fun removeAllUsers() {
        return userLocal.removeAllUsers()
    }

    // Extension functions mapping UserDb to User
    private fun UserDb.toUser(): User {
        return User(
            id = this.id.toInt(),
            username = this.username ?: "",
            avatarUrl = this.avatarUrl ?: "",
            htmlUrl = this.htmlUrl ?: "",
            location = this.location ?: "",
            followers = this.followers ?: 0,
            following = this.following ?: 0,
            blog = this.blog ?: "",
            isInDetail = this.inDetail ?: false
        )
    }
}

