package com.nalldev.gombal.domain.usecases.onboarding

import com.nalldev.gombal.domain.repositories.OnBoardingRepository

class SetOnBoardingFinished(
    private val onBoardingRepository: OnBoardingRepository
) {
    suspend operator fun invoke(onBoardingFinished: Boolean) = onBoardingRepository.setIsOnBoardingFinished(onBoardingFinished)
}