package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation_lifecycle.ui.ui_some.SomeUIState

@Composable
fun SomeChildComponents(state: State<SomeUIState>) {

    Row(
        modifier = Modifier.background(color = MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val data = state.value

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (data.loading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                OldUsersMessages(data)
                Spacer(modifier = Modifier.height(150.dp))
                AllUsers(data)
            }
        }
    }
}

@Composable
fun OldUsersMessages(data: SomeUIState) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (data.userMessages.isNotEmpty()) {
                Text(
                    text = "Old Users messages",
                    style = MaterialTheme.typography.h6
                )
            } else {
                Text(
                    text = "All Users are under 25",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn {
                val messages = data.userMessages
                items(messages.size) { index ->
                    Text(messages[index])
                }
            }
        }
    }
}

@Composable
fun AllUsers(data: SomeUIState) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "All Users",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6
            )
            LazyColumn {
                val users = data.users
                items(users.size) { index ->
                    val user = users[index]
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Name: ${user.name},  ", color = user.color)
                        Text(text = "age: ${user.age}", color = user.color)
                    }
                }
            }
        }
    }
}