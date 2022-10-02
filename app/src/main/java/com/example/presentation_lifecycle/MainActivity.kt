package com.example.presentation_lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PresentationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setContent {
            val state by viewModel.observeState.collectAsState(SimpleState.State1)
            Box(
                modifier = Modifier
                    .background(state.backgroundColor)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { viewModel.updateState(SimpleState.State1) }) {
                            Text(
                                text = "Do you love Compose?",
                                modifier = Modifier.width(100.dp)
                            )
                        }
                        Button(onClick = { viewModel.updateState(SimpleState.State2) }) {
                            Text(
                                text = "What are we doing?",
                                modifier = Modifier.width(100.dp)
                            )
                        }
                        Button(onClick = { viewModel.updateState(SimpleState.State3) }) {
                            Text(
                                text = "You are the best",
                                modifier = Modifier.width(100.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    SimpleStatefulText(state)
                }
            }
        }
    }
}

@Composable
fun SimpleStatefulText(state: SimpleState) {
    Text(
        text = state.text, color = state.textColor
    )
}