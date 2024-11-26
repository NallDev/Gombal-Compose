package com.nalldev.gombal.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nalldev.gombal.domain.usecases.dark_mode.DarkModeUseCases
import com.nalldev.gombal.domain.usecases.onboarding.OnBoardingUseCases

class MainViewModel(
    onBoardingUseCases: OnBoardingUseCases,
    darkModeUseCases: DarkModeUseCases
) : ViewModel() {
    val isDarkMode = darkModeUseCases.isDarkMode().asLiveData()

    val isOnBoardingFinished = onBoardingUseCases.isOnBoardingFinished().asLiveData()
}