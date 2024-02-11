package com.example.littlelemonandroid

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route: String = "Home"
}

object Onboarding : Destinations {
    override val route: String = "Onboarding"
}

object Profile : Destinations {
    override val route: String = "Profile"
}