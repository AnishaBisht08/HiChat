package com.example.hichat.data.model

data class Message(
    val senderID: String = "",
    val receiverID: String = "",
    val text: String = "",
    val timestamp: Long = System.currentTimeMillis()
)