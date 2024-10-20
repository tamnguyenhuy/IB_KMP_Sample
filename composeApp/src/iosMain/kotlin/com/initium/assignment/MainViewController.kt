package com.initium.assignment

import androidx.compose.ui.window.ComposeUIViewController
import com.initium.assignment.di.initKoin

fun MainViewController() = run {
    initKoin {  }
    ComposeUIViewController { App() }
}