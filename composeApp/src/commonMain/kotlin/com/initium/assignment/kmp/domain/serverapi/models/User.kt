package com.initium.assignment.kmp.domain.serverapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class User {
    @SerialName("id")
    val id: Long = 0
    @SerialName("login")
    val username: String = ""
    @SerialName("avatar_url")
    val avatarUrl: String = ""
    @SerialName("html_url")
    val htmlUrl: String = ""

    override fun toString(): String {
        return "User(id=$id, username='$username', avatarUrl='$avatarUrl')"
    }
}