package com.initium.assignment.kmp.ui.app.user.mvi

import com.initium.assignment.kmp.ui.core.mvi.UiEvent

sealed interface UserEvent: UiEvent {
    data object Init: UserEvent

    data class GoDetail(val userId: String): UserEvent
}