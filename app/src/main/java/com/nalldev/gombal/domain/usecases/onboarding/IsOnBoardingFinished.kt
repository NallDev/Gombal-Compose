package com.nalldev.gombal.domain.usecases.onboarding

import com.nalldev.gombal.domain.repositories.OnBoardingRepository

class IsOnBoardingFinished(
    private val onBoardingRepository: OnBoardingRepository
) {
    operator fun invoke() = onBoardingRepository.isOnBoardingFinished
}