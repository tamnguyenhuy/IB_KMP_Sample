package com.initium.assignment.ui.app.userdetail.mvi

import com.initium.assignment.domain.usecase.FetchUserDetailUseCase
import com.initium.assignment.ui.core.mvi.MviViewModel

class UserDetailViewModel(
    private val fetchUserDetailUseCase: FetchUserDetailUseCase
) :
    MviViewModel<UserDetailState, UserDetailEvent>(initial = UserDetailState()) {

    override fun processEvent(event: UserDetailEvent) {
        when (event) {
            is UserDetailEvent.Init -> {
                safeLaunch {
                    update { copy(isLoading = true) }
                    val userDetail = fetchUserDetailUseCase(userName = event.userName)
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