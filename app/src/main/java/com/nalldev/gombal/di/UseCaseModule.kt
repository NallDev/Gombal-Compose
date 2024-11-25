package com.nalldev.gombal.di

import com.nalldev.gombal.domain.usecases.dark_mode.DarkModeUseCases
import com.nalldev.gombal.domain.usecases.dark_mode.IsDarkMode
import com.nalldev.gombal.domain.usecases.dark_mode.SetDarkMode
import com.nalldev.gombal.domain.usecases.onboarding.IsOnBoardingFinished
import com.nalldev.gombal.domain.usecases.onboarding.OnBoardingUseCases
import com.nalldev.gombal.domain.usecases.onboarding.SetOnBoardingFinished
import org.koin.dsl.module

val useCaseModule = module {
    single { IsDarkMode(get()) }
    single { SetDarkMode(get()) }
    single { DarkModeUseCases(get(), get()) }

    single { SetOnBoardingFinished(get()) }
    single { IsOnBoardingFinished(get()) }
    single { OnBoardingUseCases(get(), get()) }
}