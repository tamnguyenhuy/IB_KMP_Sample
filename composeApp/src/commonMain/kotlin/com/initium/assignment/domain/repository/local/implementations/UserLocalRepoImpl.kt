package com.initium.assignment.domain.repository.local.implementations

import com.initium.assignment.UserDb
import com.initium.assignment.domain.repository.local.helper.DatabaseHelper
import com.initium.assignment.domain.repository.local.contracts.IUserLocalRepo
import com.initium.assignment.domain.repository.remote.serverapi.models.User

class UserLocalRepoImpl private constructor() : IUserLocalRepo {

    companion object {
        val instance by lazy { UserLocalRepoImpl() }
    }

    private val query = DatabaseHelper.instance.getDatabase().userDbQueries

    override fun getAllUsers(): List<User> {
        return query.selectAll().executeAsList().map {
            it.toUser()
        }.sortedBy { it.id }
    }

    override fun getUser(id: Int): User? {
        return query.selectById(id.toString()).executeAsOneOrNull()?.toUser()
    }

    override fun getUser(userName: String): User? {
        return query.selectByUserName(userName).executeAsOneOrNull()?.toUser()
    }

    override fun saveUser(user: User, inDetail: Boolean) {
        query.insert(user.toUserDb(inDetail))
    }

    override fun removeAllUsers() {
        query.deleteAll()
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

    /**
     * Extension function to map User to UserDb.
     *
     * @param inDetail Flag to check if user is in detail.
     *
     * @return UserDb
     */
    private fun User.toUserDb(inDetail: Boolean = false): UserDb {
        return UserDb(
            id = this.id.toString(),
            username = this.username,
            avatarUrl = this.avatarUrl,
            htmlUrl = this.htmlUrl,
            location = this.location,
            followers = this.followers,
            following = this.following,
            blog = this.blog,
            inDetail = inDetail
        )
    }
}