package com.initium.assignment.kmp.ui.app.user.mvi

import com.initium.assignment.kmp.domain.model.ListDataStruct
import com.initium.assignment.kmp.domain.services.contracts.UserService
import com.initium.assignment.kmp.ui.core.mvi.MviViewModel

class UserViewModel(
) : MviViewModel<UserState, UserEvent>(initial = UserState()) {
    override fun processEvent(event: UserEvent) {
        when (event) {
            is UserEvent.Fetch -> safeLaunch {
                if (event.isRefresh) {
                    update {
                        copy(
                            isRefreshing = true,
                            users = ListDataStruct(),
                            isHasError = false
                        )
                    }
                }

                val since =
                    if (state.value.users.dataList.isEmpty()) 0 else state.value.users.dataList.last().id
                val user = UserService.instance.fetchUser(state.value.users.itemPerPage, since)
                update {
                    copy(
                        users = state.value.users.append(user),
                        isRefreshing = false,
                        isHasError = false
                    )
                }
            }
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        println("Error: ${e.message}")
        update {
            copy(
                isRefreshing = false,
                isHasError = true
            )
        }
    }
}