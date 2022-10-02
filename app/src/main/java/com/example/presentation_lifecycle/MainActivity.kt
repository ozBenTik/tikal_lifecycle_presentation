package com.example.presentation_lifecycle

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PresentationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    FirstExample()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    SecondExample()
                }
            }

        }
    }

    @Composable
    fun SimpleStatefulText(state: SimpleState) {
        Text(
            text = state.text, color = state.textColor, style = MaterialTheme.typography.h6
        )
    }

    @Composable
    fun SimpleTextField() {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
    }

    @Composable
    fun FirstExample() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "First Example - Internal State", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(20.dp))
            SimpleTextField()
        }
    }

    @Composable
    fun SecondExample() {
        val state by viewModel.observeState.collectAsState(SimpleState.State1)
        Column(
            modifier = Modifier
                .background(state.backgroundColor)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Second Example - External State", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(state.backgroundColor),
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