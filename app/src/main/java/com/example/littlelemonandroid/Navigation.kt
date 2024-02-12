package com.example.littlelemonandroid

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstname", "")
    val lastName = sharedPreferences.getString("lastname", "")
    val email = sharedPreferences.getString("email", "")
    val startDestination = if (firstName != "" && lastName != "" && email != ""){
        Home.route
    } else {
        Onboarding.route
    }
    NavHost(navController = navController, startDestination = startDestination ){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
    }
}