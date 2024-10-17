package com.initium.assignment.kmp.domain.serverapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserDetail: User() {
    @SerialName("location")
    val location: String = ""
    @SerialName("followers")
    val followers: Long = 0
    @SerialName("following")
    val following: Long = 0

    override fun toString(): String {
        return "UserDetail(location='$location', followers=$followers, following=$following)"
    }
}