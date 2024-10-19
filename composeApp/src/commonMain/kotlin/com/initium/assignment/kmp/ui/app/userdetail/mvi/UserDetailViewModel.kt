package com.initium.assignment.kmp.ui.app.userdetail.mvi

import com.initium.assignment.kmp.domain.services.contracts.UserService
import com.initium.assignment.kmp.ui.core.mvi.MviViewModel

class UserDetailViewModel(
    private val userService: UserService = UserService.instance
) :
    MviViewModel<UserDetailState, UserDetailEvent>(initial = UserDetailState()) {

    override fun processEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.Init -> {
                safeLaunch {
                    update { copy(isLoading = true) }
                    val userDetail = userService.fetchUserDetail(userName = event.userName)
                    update {
                        copy(
                            userName = event.userName,
                            userDetail = userDetail,
                            isLoading = false
                        )
                    }
                }
            }

            UserDetailEvent.Dispose -> {
                update {
                    copy(
                        userDetail = null
                    )
                }
            }
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        update { copy(isLoading = false) }
    }
}