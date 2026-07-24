package com.example.hichat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.hichat.ui.components.SearchBar
import com.example.hichat.ui.components.UserItem
import com.example.hichat.ui.theme.BackgroundWhite
import com.example.hichat.viewmodel.ChatViewModel

@Composable
fun NewChatScreen(
    navController: NavHostController
) {

    val viewModel: ChatViewModel = viewModel()

    var search by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.getAllUsers()
    }

    val filterUsers = viewModel.users.filter {
        it.name.contains(search, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
            }

            Text(
                text = "Select Contact",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        SearchBar(
            search = search,
            onSearchChange = {
                search = it
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {

            items(filterUsers) { user ->

                UserItem(
                    user = user,
                    lastMessage = "",
                    time = "",
                    onUserClick = {
                        navController.navigate(
                            "chat/${user.uid}/${user.name}"
                        )
                    }
                )
            }
        }
    }
}