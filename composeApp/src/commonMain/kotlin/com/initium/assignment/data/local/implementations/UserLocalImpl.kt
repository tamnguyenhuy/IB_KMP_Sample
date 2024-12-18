package com.initium.assignment.data.local.implementations

import com.initium.assignment.UserDb
import com.initium.assignment.data.local.contracts.IUserLocal
import com.initium.assignment.model.User
import com.initium.assignment.kmp.domain.repository.local.db.MyDatabase

class UserLocalImpl(db: MyDatabase) : IUserLocal {
    private val query = db.userDbQueries

    override fun getAllUsers(): List<UserDb> {
        return query.selectAll().executeAsList().sortedBy { it.id }
    }

    override fun getUser(id: Int): UserDb? {
        return query.selectById(id.toString()).executeAsOneOrNull()
    }

    override fun getUser(userName: String): UserDb? {
        return query.selectByUserName(userName).executeAsOneOrNull()
    }

    override fun saveUser(user: User, inDetail: Boolean) {
        query.insert(user.toUserDb(inDetail))
    }

    override fun removeAllUsers() {
        query.deleteAll()
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