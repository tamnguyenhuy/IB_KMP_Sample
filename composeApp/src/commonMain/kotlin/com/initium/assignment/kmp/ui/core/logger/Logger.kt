package com.initium.assignment.kmp.ui.core.logger

fun logDebug(tag: Any, message: String) {
    if (tag is String) {
        println("D - $tag - $message")
    } else {
        println("D - ${tag::class.simpleName} - $message")
    }
}

fun logError(tag: Any, message: String) {
    if (tag is String) {
        println("E - $tag - $message")
    } else {
        println("E - ${tag::class.simpleName} - $message")
    }
}

fun logError(tag: Any, exception: Exception) {
    if (tag is String) {
        println("E - $tag - ${exception.message}")
    } else {
        println("E - ${tag::class.simpleName} - ${exception.message}")
    }
}
