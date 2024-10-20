package com.initium.assignment.ui.app.userdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.blog
import assignment.composeapp.generated.resources.follower
import assignment.composeapp.generated.resources.following
import assignment.composeapp.generated.resources.user_details
import com.initium.assignment.ui.app.userdetail.mvi.UserDetailEvent
import com.initium.assignment.ui.app.userdetail.mvi.UserDetailState
import com.initium.assignment.ui.app.userdetail.mvi.UserDetailViewModel
import com.initium.assignment.ui.core.base.ContentPage
import com.initium.assignment.ui.theme.Md3
import com.initium.assignment.ui.uikit.CustomTopBar
import com.initium.assignment.ui.uikit.ShimmerBox
import com.initium.assignment.ui.uikit.UserItem
import com.initium.assignment.ui.uikit.UserItemShimmer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class UserDetailPage(
    private val userName: String
) :
    ContentPage<UserDetailState, UserDetailEvent, UserDetailViewModel>() {

    @Composable
    override fun injectViewModel(): UserDetailViewModel = koinInject()

    @Composable
    override fun Body(state: UserDetailState, onEvent: (UserDetailEvent) -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTopBar(text = stringResource(Res.string.user_details)) {
                pop()
            }

            if (state.isLoading) {
                LoadingState()
            } else {
                LoadedState(state)
            }
        }
    }

    @Composable
    private fun LoadingState() {
        UserItemShimmer(1)
        UserStatsRow(
            followersCount = "",
            followingCount = "",
            isLoading = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        BlogSection(blogUrl = "", isLoading = true)
    }

    @Composable
    private fun LoadedState(state: UserDetailState) {
        val userDetail = state.userDetail ?: return
        UserItem(
            name = userDetail.username,
            link = userDetail.htmlUrl,
            imageLink = userDetail.avatarUrl,
        )
        UserStatsRow(
            followersCount = userDetail.followers.toString(),
            followingCount = userDetail.following.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        BlogSection(blogUrl = userDetail.blog)
    }

    @Composable
    private fun UserStatsRow(
        followersCount: String,
        followingCount: String,
        isLoading: Boolean = false
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoItem(
                icon = Icons.Filled.Person,
                count = followersCount,
                label = stringResource(Res.string.follower),
                isLoading = isLoading
            )
            InfoItem(
                icon = Icons.Filled.Favorite,
                count = followingCount,
                label = stringResource(Res.string.following),
                isLoading = isLoading
            )
        }
    }

    @Composable
    private fun InfoItem(icon: ImageVector, count: String, label: String, isLoading: Boolean = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Md3.colorScheme.secondary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            InfoText(text = count, style = Md3.typography.bodyLarge, isLoading = isLoading)
            InfoText(text = label, style = Md3.typography.bodyMedium, isLoading = isLoading)
        }
    }

    @Composable
    private fun InfoText(text: String, style: TextStyle, isLoading: Boolean) {
        if (isLoading) {
            ShimmerBox(64.dp, 16.dp)
        } else {
            Text(
                text = text,
                style = style,
                fontWeight = FontWeight.Bold.takeIf { style == Md3.typography.bodyLarge }
            )
        }
    }

    @Composable
    private fun BlogSection(blogUrl: String, isLoading: Boolean = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(Res.string.blog),
                style = Md3.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            if (isLoading) {
                ShimmerBox(200.dp, 16.dp)
            } else {
                Text(
                    text = blogUrl,
                    style = Md3.typography.titleSmall
                )
            }
        }
    }

    override fun onStarted() {
        super.onStarted()
        submit(UserDetailEvent.Init(userName))
    }

    override fun onDisposed() {
        super.onDisposed()
        submit(UserDetailEvent.Dispose)
    }
}