package com.initium.assignment.kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.initium.assignment.kmp.ui.di.initKoin

fun MainViewController() = run {
    initKoin {  }
    ComposeUIViewController { App() }
}