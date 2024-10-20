package com.initium.assignment.ui.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.initium.assignment.ui.theme.Md3

@Composable
fun CustomTopBar(text: String, onBackClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(bottom = 4.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back arrow icon
        val isShowBack = onBackClick != null
        if (isShowBack) {
            Row(
                Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onBackClick?.invoke() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
                    tint = Md3.colorScheme.scrim
                )
            }
        }

        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f),
            color = MaterialTheme.colorScheme.scrim,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        if (isShowBack) {
            Box(modifier = Modifier.weight(1f))
        }
    }
}