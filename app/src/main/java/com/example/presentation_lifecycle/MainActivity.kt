package com.example.presentation_lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
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

    @OptIn(ExperimentalFoundationApi::class)
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
                        .weight(0.5f)
                ) {
                    FirstExample()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                ) {
                    SecondExample()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    ThirdExample()
                }
            }

        }
    }

    @Composable
    fun SimpleStatefulText(state: SimpleState?) {
        state?.let {
            Log.d("Compose", "Composing.... ${state.text}")
            Text(
                text = state.text, color = state.textColor, style = MaterialTheme.typography.h6
            )
        }
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
        val state by viewModel.observeSimpleState.collectAsState(SimpleState.State1)
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
                Button(onClick = { viewModel.updateSimpleState(SimpleState.State1) }) {
                    Text(
                        text = "Do you love Compose?",
                        modifier = Modifier.width(100.dp)
                    )
                }
                Button(onClick = { viewModel.updateSimpleState(SimpleState.State2) }) {
                    Text(
                        text = "What are we doing?",
                        modifier = Modifier.width(100.dp)
                    )
                }
                Button(onClick = { viewModel.updateSimpleState(SimpleState.State3) }) {
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

    @ExperimentalFoundationApi
    @Composable
    fun ThirdExample() {
        val state by viewModel.observeExtendedState.collectAsState(ExtendedState.EMPTY)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Third Example - External State", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
            ) {
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                nameState = SimpleState.State2
                            )
                        )
                    }) {
                        Text(
                            text = "Update Name Text only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                emailState = SimpleState.State1
                            )
                        )
                    }) {
                        Text(
                            text = "Update Email Text only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                listState = ListState.LargeList
                            )
                        )
                    }) {
                        Text(
                            text = "Update list only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                nameState = SimpleState.State1
                            )
                        )
                    }) {
                        Text(
                            text = "Update Name Text only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                emailState = SimpleState.State2
                            )
                        )
                    }) {
                        Text(
                            text = "Update Email Text only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
                item {
                    Button(onClick = {
                        viewModel.updateExtendedState(
                            viewModel.observeExtendedState.value.copy(
                                listState = ListState.SmallList
                            )
                        )
                    }) {
                        Text(
                            text = "Update list only",
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Name:")
                SimpleStatefulText(state.nameState)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "email:")
                SimpleStatefulText(state.emailState)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "list:")
                LazyRow {
                    items(state.listState?.numberOfItems ?: 0) { item ->
                        Card(
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = " ITEM number->$item ")
                        }
                    }

                }
            }
        }
    }
}