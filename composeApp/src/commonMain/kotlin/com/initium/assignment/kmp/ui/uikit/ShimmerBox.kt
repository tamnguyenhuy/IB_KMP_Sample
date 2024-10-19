//
// Copyright (c) Safetrust, Inc. - All Rights Reserved
// Unauthorized copying of this file, via any medium is strictly prohibited
// Proprietary and confidential
//

package com.initium.assignment.kmp.ui.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.initium.assignment.kmp.ui.uikit.extension.shimmerEffect

@Composable
fun ShimmerBox(with: Dp, height: Dp) {
    Box(
        Modifier
            .width(with)
            .height(height)
            .shimmerEffect()
    )
}