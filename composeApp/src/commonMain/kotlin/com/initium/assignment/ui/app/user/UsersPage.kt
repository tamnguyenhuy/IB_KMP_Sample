package com.initium.assignment.ui.app.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.github_users
import com.initium.assignment.ui.app.user.mvi.UserEvent
import com.initium.assignment.ui.app.user.mvi.UserState
import com.initium.assignment.ui.app.user.mvi.UserViewModel
import com.initium.assignment.ui.app.userdetail.UserDetailPage
import com.initium.assignment.ui.core.base.ContentPage
import com.initium.assignment.ui.uikit.CustomTopBar
import com.initium.assignment.ui.uikit.GenericLazyList
import com.initium.assignment.ui.uikit.UserItem
import com.initium.assignment.ui.uikit.UserItemShimmer
import com.initium.assignment.ui.uikit.rememberGenericLazyListState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class UsersPage: ContentPage<UserState, UserEvent, UserViewModel>() {

    @Composable
    override fun injectViewModel(): UserViewModel = koinInject()

    @Composable
    override fun Body(state: UserState, onEvent: (UserEvent) -> Unit) {
        val listState = rememberGenericLazyListState(
            source = state.users,
            isHasFailure = state.isHasFailure,
            onFetch = { page ->
                onEvent(UserEvent.Fetch(page, false))
            },
            onRefresh = {
                onEvent(UserEvent.Fetch(0, true))
            }
        )

        Column(modifier = Modifier.fillMaxSize()) {
            CustomTopBar(text = stringResource(Res.string.github_users))
            GenericLazyList(
                state = listState,
                itemKey = { user -> user.username },
                itemView = { _, user ->
                    UserItem(
                        name = user.username,
                        link = user.htmlUrl,
                        imageLink = user.avatarUrl,
                        onClick = {
                            pushTo(UserDetailPage(user.username))
                        }
                    )
                },
                loadingIndicator = {
                    UserItemShimmer()
                }
            )
        }
    }
}