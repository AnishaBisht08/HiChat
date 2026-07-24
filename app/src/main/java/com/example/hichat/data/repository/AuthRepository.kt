package com.example.hichat.data.repository

import com.example.hichat.data.firebase.FirebaseManager
import com.example.hichat.data.model.User

class AuthRepository {

    private val auth = FirebaseManager.auth
    private val firestore = FirebaseManager.firestore


    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                val uid = auth.currentUser?.uid ?: ""

                val user = User(
                    uid = uid,
                    name = name,
                    email = email
                )

                firestore.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        onResult(true, null)
                    }
                    .addOnFailureListener { e ->
                        onResult(false, e.message)
                    }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }


    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                onResult(true,null)
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }



    fun getCurrentUser(onResult: (User?) -> Unit){

        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                onResult(document.toObject(User::class.java))
            }
            .addOnFailureListener {
                onResult(null)
            }
    }


    fun updateAvatar(
        avatar: String,
        onComplete: () -> Unit
    ) {
        val uid = FirebaseManager.auth.currentUser!!.uid

        FirebaseManager.firestore
            .collection("users")
            .document(uid)
            .update("avatar", avatar)
            .addOnSuccessListener {
                onComplete()
            }
    }

    fun logout(){
        auth.signOut()
    }
}