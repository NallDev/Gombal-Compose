package com.nalldev.gombal.di

import com.nalldev.gombal.data.repositories.DarkModeRepositoryImpl
import com.nalldev.gombal.data.repositories.OnBoardingRepositoryImpl
import com.nalldev.gombal.domain.repositories.DarkModeRepository
import com.nalldev.gombal.domain.repositories.OnBoardingRepository
import org.koin.dsl.module

val repositoryModule = module {
    single <DarkModeRepository>{ DarkModeRepositoryImpl(get()) }
    single <OnBoardingRepository>{
        OnBoardingRepositoryImpl(
            get()
        )
    }
}