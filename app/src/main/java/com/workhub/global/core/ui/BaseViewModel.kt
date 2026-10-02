package com.workhub.global.core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S>(
    initialState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)

    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val currentState: S
        get() = _uiState.value

    protected fun updateState(
        reducer: S.() -> S
    ) {
        _uiState.update { state ->
            state.reducer()
        }
    }
}