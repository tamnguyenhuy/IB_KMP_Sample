package com.initium.assignment.kmp.ui.app.userdetail.mvi

import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.UserDetail
import com.initium.assignment.kmp.ui.core.mvi.UiState

data class UserDetailState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
) : UiState
