package com.example.hichat.data.repository

import com.example.hichat.data.firebase.FirebaseManager
import com.example.hichat.data.model.Message
import com.example.hichat.data.model.User
import com.google.firebase.firestore.Query

class ChatRepository {

    private val auth = FirebaseManager.auth
    private val firestore = FirebaseManager.firestore


    fun getChatID(receiverID: String): String {
        val currentID = auth.currentUser!!.uid
        return if (currentID < receiverID) "${currentID}_$receiverID"
        else "${receiverID}_$currentID"
    }


    fun sendMessage(
        receiverID: String,
        text: String,
        onResult: (Boolean) -> Unit
    ) {

        val senderID = auth.currentUser!!.uid

        val message = Message(
            senderID = senderID,
            receiverID = receiverID,
            text = text
        )

        firestore.collection("chats")
            .document(getChatID(receiverID))
            .collection("messages")
            .add(message)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }

    }


    fun getMessage(
        receiverID: String,
        onResult: (List<Message>) -> Unit
    ) {

        firestore.collection("chats")
            .document(getChatID(receiverID))
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { value, _ ->

                val list = mutableListOf<Message>()

                value?.documents?.forEach {
                    list.add(it.toObject(Message::class.java)!!)
                }

                onResult(list)
            }

    }


    fun getAllUsers(
        onResult: (List<User>) -> Unit
    ){

        val currentUserID = auth.currentUser?.uid

        firestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val userList = mutableListOf<User>()

                for (document in result){
                    val user = document.toObject(User::class.java)

                    if (user.uid != currentUserID){
                        userList.add(user)
                    }
                }

                onResult(userList)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }

    }


    fun getLastMessage(
        receiverID: String,
        onResult: (Message?) -> Unit
    ) {
        val currentUid = auth.currentUser!!.uid
         val chatID =
             if (currentUid < receiverID) "${currentUid}_$receiverID"
        else
         "${receiverID}_$currentUid"

        firestore.collection("chats")
            .document(chatID)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { value, _ ->

                val message = value?.documents?.firstOrNull()?.toObject(Message::class.java)
                onResult(message)
            }

    }
}