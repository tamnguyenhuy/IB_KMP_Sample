package com.initium.assignment.model

data class User(
    val id: Int = 0,

    val username: String = "",

    val avatarUrl: String = "",

    val htmlUrl: String = "",

    val location: String = "",

    val followers: Long = 0,

    val following: Long = 0,

    val blog: String = "",

    val isInDetail: Boolean = false
)