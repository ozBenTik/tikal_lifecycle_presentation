package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import com.example.presentation_lifecycle.ui.ui_some.PresentationViewModel

@Composable
fun SomeScreen(
    viewModel: PresentationViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    SomeLifecycleObserver(lifecycleOwner.lifecycle){
        viewModel.fetchData()
    }

    val state = viewModel.uiState.collectAsState()
    SomeChildComponents(state)
}