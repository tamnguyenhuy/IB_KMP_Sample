package com.initium.assignment.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform