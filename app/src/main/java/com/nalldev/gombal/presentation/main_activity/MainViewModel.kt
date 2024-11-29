package com.nalldev.gombal.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.gombal.domain.usecases.dark_mode.DarkModeUseCases
import com.nalldev.gombal.domain.usecases.onboarding.OnBoardingUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class MainViewModel(
    onBoardingUseCases: OnBoardingUseCases,
    darkModeUseCases: DarkModeUseCases
) : ViewModel() {
    val isDarkMode = darkModeUseCases.isDarkMode().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = runBlocking { darkModeUseCases.isDarkMode().first() }
    )

    val isOnBoardingFinished = onBoardingUseCases.isOnBoardingFinished().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = runBlocking { onBoardingUseCases.isOnBoardingFinished().first() }
    )
}