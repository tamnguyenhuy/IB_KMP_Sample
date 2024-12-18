package com.initium.assignment.ui.app.user.mvi

import com.initium.assignment.model.ListDataStruct
import com.initium.assignment.model.User
import com.initium.assignment.ui.core.mvi.UiState

data class UserState(
    val users: ListDataStruct<User> = ListDataStruct(),
    val isRefreshing: Boolean = false,
    val isHasFailure: Boolean = false
) : UiState {


}