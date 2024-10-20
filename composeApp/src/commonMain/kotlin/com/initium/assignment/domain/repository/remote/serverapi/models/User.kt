package com.initium.assignment.domain.repository.remote.serverapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class User(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("login")
    val username: String = "",

    @SerialName("avatar_url")
    val avatarUrl: String = "",

    @SerialName("html_url")
    val htmlUrl: String = "",

    @SerialName("location")
    val location: String = "",

    @SerialName("followers")
    val followers: Long = 0,

    @SerialName("following")
    val following: Long = 0,

    @SerialName("blog")
    val blog: String = "",

    val isInDetail: Boolean = false
) {
    override fun toString(): String {
        return "UserDetail(location='$location', followers=$followers, following=$following)"
    }
}