package com.initium.assignment.kmp.ui.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.initium.assignment.kmp.ui.theme.Md3
import com.initium.assignment.kmp.ui.uikit.extension.shimmerEffect

@Composable
private fun UserCard(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clickable(enabled = onClick != null, onClick = onClick ?: {})
        ,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Adjust this value if needed
    ) {
        content()
    }
}

@Composable
fun UserItem(name: String, link: String, imageLink: String, onClick: (() -> Unit)? = null) {
    UserCard {
        Row(
            modifier = Modifier
                .clickable {
                    onClick?.invoke()
                }.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(Md3.shapes.small)
                    .background(Md3.colorScheme.secondaryContainer.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageLink,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp).clip(CircleShape),
                )
            }

            // User details (name and link)
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = Md3.typography.titleLarge,
                    color = Md3.colorScheme.scrim,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = link,
                    style = Md3.typography.bodyMedium.copy(color = Color.Blue),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun UserItemShimmer(size: Int = 3) {
    repeat(size) {
        UserCard {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(Md3.shapes.small)
                        .shimmerEffect()
                )

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(24.dp)
                            .clip(Md3.shapes.small)
                            .shimmerEffect()
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(24.dp)
                            .clip(Md3.shapes.small)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}