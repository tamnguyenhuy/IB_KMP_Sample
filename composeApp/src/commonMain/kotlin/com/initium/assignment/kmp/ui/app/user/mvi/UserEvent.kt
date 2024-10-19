package com.initium.assignment.kmp.ui.app.user.mvi

import com.initium.assignment.kmp.ui.core.mvi.UiEvent

sealed interface UserEvent: UiEvent {
    data class Fetch(val page: Int, val isRefresh: Boolean = false): UserEvent
}