package com.example.presentation_lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation_lifecycle.ui.ui_some.compose.SomeScreen
import com.example.presentation_lifecycle.ui.ui_some.PresentationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PresentationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setContent {
            SomeScreen(viewModel)
        }
    }
}