package com.initium.assignment.ui.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.initium.assignment.ui.core.logger.logDebug
import com.initium.assignment.ui.core.logger.logError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


abstract class MviViewModel<STATE : UiState, EVENT : UiEvent>(initial: STATE) : ViewModel() {

    private val _state = MutableStateFlow(initial)
    val state: StateFlow<STATE> get() = _state.asStateFlow()
    private val tasks = mutableMapOf<String, Job>()

    private val _handler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        logError(this, "has exception: $throwable")
    }

    private val coroutineIoScope: CoroutineScope = CoroutineScope(Dispatchers.IO + _handler)

    abstract fun processEvent(event: EVENT)

    open fun onError(e: Exception) {}

    protected fun setState(tag: String = "", state: STATE.() -> STATE): Boolean {
        if (!viewModelScope.isActive) {
            logDebug(this, "[$tag] update is cancelled")
            return false
        }

        val newState = state.invoke(_state.value)
        return _state.tryEmit(newState)
    }

    protected fun safeLaunch(key: String = "single", block: suspend CoroutineScope.() -> Unit) {
        tasks[key] = viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun cancel(key: String) {
        tasks[key]?.cancel()
        tasks.remove(key)
    }

    protected fun cancelAll() {
        for (job in tasks.values) {
            job.cancel()
        }
        tasks.clear()
    }

    protected fun update(tag: String = "", state: STATE.() -> STATE) {
        if (!coroutineIoScope.isActive) {
            logDebug(this, "[$tag] update is cancelled")
            return
        }
        val newState = state.invoke(_state.value)
//        _state.value.printDiff(tag, newState)
        val success = _state.tryEmit(newState)
        /* if (success) {
             logVerbose(this, "[$tag] update -> newState=$newState")
         }*/
    }

//    protected fun safeLaunch(block: suspend () -> Unit): Job {
//        return coroutineIoScope.launch {
//            try {
//                block.invoke()
//            } catch (e: Exception) {
//                logError(this@MviViewModel, "runCatching exception: $e - ${e.stackTraceToString()}")
//            }
//        }
//    }
}