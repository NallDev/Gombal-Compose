package com.nalldev.gombal.presentation.screens.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.gombal.domain.usecases.onboarding.OnBoardingUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val onBoardingUseCases: OnBoardingUseCases) : ViewModel() {
    private val _page = MutableStateFlow(1)
    val page: StateFlow<Int> = _page.asStateFlow()

    var onBoardingFinished by mutableStateOf(false)
        private set

    var isTransitioning by mutableStateOf(false)
        private set

    fun nextPage() {
        if (isTransitioning) return
        if (page.value == 3) {
            onBoardingFinished = true
        } else {
            isTransitioning = true
            _page.value += 1
        }
    }

    fun finishTransition() {
        isTransitioning = false
    }

    fun setOnBoardingFinished() = viewModelScope.launch {
        onBoardingUseCases.setOnBoardingFinished(false)
    }
}
