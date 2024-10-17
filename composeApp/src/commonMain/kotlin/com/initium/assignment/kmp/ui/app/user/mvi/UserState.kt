package com.initium.assignment.kmp.ui.app.user.mvi

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.initium.assignment.kmp.domain.serverapi.models.User
import com.initium.assignment.kmp.ui.core.mvi.UiState

data class UserState(
    val users: SnapshotStateList<User> = SnapshotStateList(),
) : UiState {


}