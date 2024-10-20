package com.initium.assignment.ui.app.userdetail.mvi

import com.initium.assignment.domain.repository.remote.serverapi.models.User
import com.initium.assignment.ui.core.mvi.UiState

data class UserDetailState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val userDetail: User? = null,
) : UiState
