package com.initium.assignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.jetpack.ProvideNavigatorLifecycleKMPSupport
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.initium.assignment.ui.app.user.UsersPage
import com.initium.assignment.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalVoyagerApi::class)
@Composable
@Preview
fun App() = AppTheme {
    ProvideNavigatorLifecycleKMPSupport {
        Navigator(UsersPage()) {
            Scaffold(
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        CurrentScreen()
                    }
                },
            )
        }
    }
}