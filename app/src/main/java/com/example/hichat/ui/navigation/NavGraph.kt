package com.example.hichat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hichat.data.firebase.FirebaseManager
import com.example.hichat.ui.screens.ChatScreen
import com.example.hichat.ui.screens.HomeScreen
import com.example.hichat.ui.screens.LoginScreen
import com.example.hichat.ui.screens.NewChatScreen
import com.example.hichat.ui.screens.ProfileScreen
import com.example.hichat.ui.screens.RegisterScreen
import com.example.hichat.ui.screens.WelcomeScreen
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val startDestination =
        if (FirebaseManager.auth.currentUser != null) {
            Screen.Home.route
        } else {
            Screen.Welcome.route
        }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onSignUpClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.NewChat.route) {
            NewChatScreen(navController)
        }

        composable(
            route = "chat/{uid}/{name}",
            arguments = listOf(
                navArgument("uid") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val uid = backStackEntry.arguments?.getString("uid") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""

            ChatScreen(
                receiverID = uid,
                receiverName = name,
                navController = navController
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                navController = navController
            )
        }

   }
}