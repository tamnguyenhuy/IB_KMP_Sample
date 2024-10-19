package com.initium.assignment.kmp.domain.model

data class ErrorDetail(
    override val message: String,
    val errorCode: ErrorCode
): Exception() {
    override fun toString(): String {
        return "ErrorDetail(message='$message', errorCode=$errorCode)"
    }
}