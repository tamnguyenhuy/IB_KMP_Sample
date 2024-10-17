package com.initium.assignment.kmp.ui.app.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.github_users
import com.initium.assignment.kmp.ui.app.user.mvi.UserEvent
import com.initium.assignment.kmp.ui.app.user.mvi.UserState
import com.initium.assignment.kmp.ui.app.user.mvi.UserViewModel
import com.initium.assignment.kmp.ui.core.base.ContentPage
import com.initium.assignment.kmp.ui.uikit.CustomTopBar
import com.initium.assignment.kmp.ui.uikit.UserItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class UsersPage: ContentPage<UserState, UserEvent, UserViewModel>() {

    @Composable
    override fun injectViewModel(): UserViewModel = koinInject()

    @Composable
    override fun Body(state: UserState, onEvent: (UserEvent) -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTopBar(text = stringResource(Res.string.github_users))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.users) { user ->
                    UserItem(
                        name = user.username,
                        link = user.htmlUrl,
                        imageRes = 0
                    )
                }
            }
        }
    }
}