package com.initium.assignment.kmp.ui.app.userdetail.mvi

import com.initium.assignment.kmp.ui.core.mvi.UiEvent

sealed interface UserDetailEvent: UiEvent {
    data class Init(val userName: String): UserDetailEvent
    data object Dispose: UserDetailEvent
}