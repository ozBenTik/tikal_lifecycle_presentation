package com.example.presentation_lifecycle.ui.ui_some

import com.example.presentation_lifecycle.model.SomeData

data class SomeUIState(
    val users: List<SomeData> = emptyList(),
    val relevantUsers: List<SomeData> = emptyList(),
    val loading: Boolean = true
)