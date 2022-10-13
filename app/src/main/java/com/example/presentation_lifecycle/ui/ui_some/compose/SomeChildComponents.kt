package com.example.presentation_lifecycle.ui.ui_some.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation_lifecycle.ui.ui_some.SomeUIState

@Composable
fun SomeChildComponents(state: State<SomeUIState>) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val data = state.value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (data.loading) {
                CircularProgressIndicator()
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    AllUsers(
                        data, modifier = Modifier
                            .padding(bottom = 20.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth()
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    OldUsersMessages(
                        data, modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun OldUsersMessages(data: SomeUIState, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(16.dp),
        elevation = 16.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .background( if (data.relevantUsers.isNotEmpty()) Color.LightGray else Color.Red)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val hasUsers = data.relevantUsers.isNotEmpty()

                Text(
                    text = if (hasUsers) {
                        "25+ Users Fav Sports"
                    } else {
                        "All Users are under 25"
                    },
                    color = if (hasUsers) {
                        MaterialTheme.colors.onBackground
                    } else {
                        MaterialTheme.colors.background
                    },
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(6.dp)
                )
            }
            LazyColumn {
                val relevantUsers = data.relevantUsers
                items(relevantUsers.size) { index ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.padding(horizontal = 50.dp, vertical = 6.dp)
                    ) {
                        val user = relevantUsers[index]
                        Text(user.message, modifier = Modifier.weight(0.9f))
                        Icon(
                            painter = painterResource(id = data.users[index].favSportIconRes),
                            contentDescription = "User ${user.name} sport icon",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .weight(0.1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AllUsers(data: SomeUIState, modifier: Modifier = Modifier) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
        elevation = 16.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            val hasUsers = data.users.isNotEmpty()
            Row(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = if (hasUsers) "All Users" else "No Users",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(6.dp)
                )
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val users = data.users
                items(users.size) { index ->
                    val user = users[index]
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(horizontal = 50.dp, vertical = 6.dp)
                    ) {
                        Text(text = "Name: ${user.name},  ", color = user.color)
                        Text(text = "age: ${user.age}", color = user.color)
                    }
                }
            }
        }
    }
}