package com.initium.assignment.ui.app.userdetail.mvi

import com.initium.assignment.model.User
import com.initium.assignment.ui.core.mvi.UiState

data class UserDetailState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val userDetail: User? = null,
) : UiState
