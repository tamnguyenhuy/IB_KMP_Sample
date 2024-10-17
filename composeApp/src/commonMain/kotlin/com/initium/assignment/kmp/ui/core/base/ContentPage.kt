package com.initium.assignment.kmp.ui.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.initium.assignment.kmp.ui.core.mvi.MviViewModel
import com.initium.assignment.kmp.ui.core.mvi.UiEvent
import com.initium.assignment.kmp.ui.core.mvi.UiState


abstract class ContentPage<S : UiState, E : UiEvent, VM : MviViewModel<S, E>> : Screen {


    private lateinit var viewModel: VM
    private lateinit var navigator: Navigator


    @Composable
    abstract fun injectViewModel(): VM

    @Composable
    override fun Content() {
        navigator = LocalNavigator.currentOrThrow
        viewModel = injectViewModel()
        val state by viewModel.state.collectAsState()

        DisposableEffect(key) {
            onStarted()
            onDispose {
                onDisposed()
            }
        }


        Body(state, ::submit)
    }


    @Composable
    abstract fun Body(state: S, onEvent: (E) -> Unit)

    open fun onStarted() {}

    open fun onDisposed() {}

    protected fun submit(event: E) {
        viewModel.processEvent(event)
    }

    protected fun pushTo(page: Screen) {
        navigator.push(page)
    }

    protected fun replace(page: Screen) {
        navigator.replace(page)
    }

    protected fun pop() {
        navigator.pop()
    }
}