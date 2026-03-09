package com.initium.assignment.ui.app.user.mvi

import com.initium.assignment.domain.model.ListDataStruct
import com.initium.assignment.domain.usecase.ClearUserCacheUseCase
import com.initium.assignment.domain.usecase.FetchUsersUseCase
import com.initium.assignment.ui.core.mvi.MviViewModel

class UserViewModel(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val clearUserCacheUseCase: ClearUserCacheUseCase
) : MviViewModel<UserState, UserEvent>(initial = UserState()) {

    override fun processEvent(event: UserEvent) {
        when (event) {
            is UserEvent.Fetch -> safeLaunch {
                if (event.isRefresh) {
                    clearUserCacheUseCase()
                    update {
                        copy(
                            isRefreshing = true,
                            users = ListDataStruct(),
                            isHasFailure = false
                        )
                    }
                }
                val users = state.value.users
                // since is the id of the last user in the existing list or 0 if the list is empty
                val since = if (users.dataList.isEmpty()) 0 else users.dataList.last().id

                val user = fetchUsersUseCase(users.itemPerPage, since)
                update {
                    copy(
                        users = users.append(user),
                        isRefreshing = false,
                        isHasFailure = false
                    )
                }
            }
        }
    }

    override fun onError(e: Exception) {
        super.onError(e)
        update {
            copy(
                isRefreshing = false,
                isHasFailure = true
            )
        }
    }
}