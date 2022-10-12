package com.example.presentation_lifecycle.ui.ui_some

import com.example.presentation_lifecycle.model.SomeData

data class SomeUIState(
    val users: List<SomeData> = emptyList(),
    val userMessages: List<String> = emptyList(),
    val loading: Boolean = true
)