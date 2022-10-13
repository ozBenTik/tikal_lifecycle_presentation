package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LifecycleOwner
import com.example.presentation_lifecycle.ui.ui_some.SomeViewModel

@Composable
fun SomeScreen(
    viewModel: SomeViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    LifecycleAwareComposable(lifecycleOwner) {
        viewModel.fetchSomeData()
    }

    MaterialTheme {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = {
                TopAppBar {
                    Text(text = "Our Sports Team", style = MaterialTheme.typography.h5, color = MaterialTheme.colors.surface, fontWeight = FontWeight.Medium)
                }
            },
            backgroundColor = MaterialTheme.colors.background
        ) { contentPadding ->
            Box(Modifier.padding(contentPadding)) {
                val state = viewModel.uiState.collectAsState()
                SomeChildComponents(state)
            }
        }
    }

}