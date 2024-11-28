package com.nalldev.gombal.presentation.navigation

import com.nalldev.gombal.domain.model.JobModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object OnBoarding : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data class Detail(val jobModel: JobModel) : Screen()
}