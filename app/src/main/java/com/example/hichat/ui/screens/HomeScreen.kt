package com.example.hichat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.hichat.ui.components.HomeTopBar
import com.example.hichat.ui.components.SearchBar
import com.example.hichat.ui.components.UserItem
import com.example.hichat.ui.navigation.Screen
import com.example.hichat.ui.theme.BackgroundWhite
import com.example.hichat.ui.theme.PrimaryBlue
import com.example.hichat.utils.Time
import com.example.hichat.viewmodel.ChatViewModel

@Composable
fun HomeScreen(navController: NavHostController) {

    val viewModel: ChatViewModel = viewModel()
    var search by remember { mutableStateOf("") }
    val filterUsers = viewModel.users.filter {
        it.name.contains(search, ignoreCase = true)
    }

    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.NewChat.route)
                },
                containerColor = PrimaryBlue
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Chat,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(BackgroundWhite)
    ) {

        HomeTopBar(
            onProfileClick = {
                navController.navigate(Screen.Profile.route)
            }
        )

        SearchBar(
            search = search,
            onSearchChange = {
                search = it
            }
        )

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {

            items(filterUsers) { user ->

                LaunchedEffect(user.uid) {
                    viewModel.loadLastMessages(user.uid)
                }

                val lastMessage = viewModel.lastMessages[user.uid]

                UserItem(
                    user,
                    lastMessage = lastMessage?.text ?: "Start Chatting...",
                    time = lastMessage?.timestamp?.let { Time.formatTime(it) } ?: "",
                    onUserClick = {
                        navController.navigate(
                            "chat/${user.uid}/${user.name}"
                        )
                    })

            }

        }
    }

    }
}