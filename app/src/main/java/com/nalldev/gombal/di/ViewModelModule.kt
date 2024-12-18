package com.nalldev.gombal.di

import com.nalldev.gombal.presentation.main_activity.MainViewModel
import com.nalldev.gombal.presentation.screens.bookmark.BookmarkViewModel
import com.nalldev.gombal.presentation.screens.detail.DetailViewModel
import com.nalldev.gombal.presentation.screens.home.HomeViewModel
import com.nalldev.gombal.presentation.screens.onboarding.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { OnBoardingViewModel(get())}
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { BookmarkViewModel(get()) }
}