package com.example.hichat.ui.navigation

sealed class Screen(val route: String){
  object Welcome: Screen("welcome")
  object Login: Screen("login")
  object Register: Screen("register")
  object Home: Screen("home")
  object NewChat: Screen("new_chat")
  object Profile: Screen("profile")

}