package com.example.hichat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hichat.data.model.Message
import com.example.hichat.data.model.User
import com.example.hichat.data.repository.ChatRepository

class ChatViewModel: ViewModel() {

    private val repository = ChatRepository()


    var messages by mutableStateOf<List<Message>>(emptyList())
        private set

    var users by mutableStateOf<List<User>>(emptyList())
        private set
    val lastMessages = mutableStateMapOf<String, Message>()


    fun getAllUsers(){
        repository.getAllUsers { list ->
            users = list
        }
    }

    fun sendMessage(
        receiverID: String,
        text: String
    ){
        repository.sendMessage(receiverID,text){ }
    }

    fun getMessages(
        receiverID: String
    ){
        repository.getMessage(receiverID){
            messages = it
        }
    }


    fun loadLastMessages(uid: String) {

        repository.getLastMessage(
          receiverID = uid
        ) { message ->

            if (message != null) {
                lastMessages[uid] = message
            }
        }
    }

}