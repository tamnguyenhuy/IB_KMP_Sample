package com.initium.assignment.ui.app.user.mvi

import com.initium.assignment.domain.model.ListDataStruct
import com.initium.assignment.domain.repository.remote.serverapi.models.User
import com.initium.assignment.ui.core.mvi.UiState

data class UserState(
    val users: ListDataStruct<User> = ListDataStruct(),
    val isRefreshing: Boolean = false,
    val isHasFailure: Boolean = false
) : UiState {


}