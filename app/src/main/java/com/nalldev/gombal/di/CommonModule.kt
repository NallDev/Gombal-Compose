package com.nalldev.gombal.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
}