package com.example.presentation_lifecycle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PresentationViewModel : ViewModel() {
    private val _simpleState = MutableStateFlow<SimpleState>(SimpleState.State1)
    private val _extendedState = MutableStateFlow<ExtendedState>(ExtendedState.EMPTY)

    val observeSimpleState = _simpleState.asStateFlow()

    fun updateSimpleState(newSimpleState: SimpleState) {
        _simpleState.tryEmit(newSimpleState)
    }

    val observeExtendedState = _extendedState.asStateFlow()

    fun updateExtendedState(newExtendedState: ExtendedState) {
        _extendedState.tryEmit(newExtendedState)
    }
}