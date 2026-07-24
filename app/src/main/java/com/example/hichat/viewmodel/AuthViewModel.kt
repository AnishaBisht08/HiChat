package com.example.hichat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hichat.data.model.User
import com.example.hichat.data.repository.AuthRepository

class AuthViewModel: ViewModel() {
    private val repository = AuthRepository()
    var isLoading by mutableStateOf(false)
        private set


    var currentUser by mutableStateOf<User?>(null)
        private set



    fun registerUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ){
        isLoading = true

        repository.registerUser(
            name = name,
            email = email,
            password = password){ success , error ->

            isLoading = false

            onResult(success,error)
        }
    }


    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        isLoading = true

        repository.loginUser(
            email = email,
            password = password
        ){ success , error ->

            isLoading = false

            onResult(success,error)
        }
    }

    fun getCurrentUser(){

        repository.getCurrentUser {
            currentUser = it
        }
    }

    fun logout(){
        repository.logout()
    }

    fun updateAvatar(name: String) {
        repository.updateAvatar(name) {
            getCurrentUser()
        }
    }

}