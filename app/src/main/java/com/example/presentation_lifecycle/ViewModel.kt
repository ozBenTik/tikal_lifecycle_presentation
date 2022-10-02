package com.example.presentation_lifecycle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PresentationViewModel : ViewModel() {
    private val _state = MutableStateFlow<SimpleState>(SimpleState.State1)

    val observeState = _state.asStateFlow()

    fun updateState(newState: SimpleState) {
        _state.tryEmit(newState)
    }
}