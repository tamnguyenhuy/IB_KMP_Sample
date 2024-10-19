package com.initium.assignment.kmp.ui.app.user.mvi

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.initium.assignment.kmp.domain.model.ListDataStruct
import com.initium.assignment.kmp.domain.repository.remote.serverapi.models.User
import com.initium.assignment.kmp.ui.core.mvi.UiState

data class UserState(
    val users: ListDataStruct<User> = ListDataStruct(),
    val isRefreshing: Boolean = false,
    val isHasError: Boolean = false
) : UiState {


}