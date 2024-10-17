package com.initium.assignment.kmp.ui.app.user.mvi

import androidx.compose.runtime.toMutableStateList
import com.initium.assignment.kmp.domain.serverapi.apis.UserApi
import com.initium.assignment.kmp.ui.core.mvi.MviViewModel

class UserViewModel : MviViewModel<UserState, UserEvent>(initial = UserState()) {

    init {
        processEvent(UserEvent.Init)
    }


    override fun processEvent(event: UserEvent) {
        when (event) {
            is UserEvent.GoDetail -> {

            }

            is UserEvent.Init -> safeLaunch {
                val hello = UserApi("https://api.github.com").fetchUser(20, 0)
                update {
                    copy(users = hello.toMutableList().toMutableStateList())
                }
            }
        }
    }
}