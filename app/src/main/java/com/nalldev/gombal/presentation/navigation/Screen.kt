package com.nalldev.gombal.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object OnBoarding : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data class Detail(val user: User) : Screen()
}