package com.nalldev.gombal.di

import com.nalldev.gombal.presentation.main_activity.MainViewModel
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { OnBoardingViewModel(get())}
}