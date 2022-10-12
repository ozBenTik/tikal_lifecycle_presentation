package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import com.example.presentation_lifecycle.ui.ui_some.SomeViewModel

@Composable
fun SomeScreen(
    viewModel: SomeViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    SomeLifecycleObserver(lifecycleOwner.lifecycle){
        viewModel.fetchSomeData()
    }

    val state = viewModel.uiState.collectAsState()
    SomeChildComponents(state)
}