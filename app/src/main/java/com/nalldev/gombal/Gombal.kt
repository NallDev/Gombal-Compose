package com.nalldev.gombal

import android.app.Application
import com.nalldev.gombal.di.commonModule
import com.nalldev.gombal.di.dataSourceModule
import com.nalldev.gombal.di.localModule
import com.nalldev.gombal.di.networkModule
import com.nalldev.gombal.di.repositoryModule
import com.nalldev.gombal.di.useCaseModule
import com.nalldev.gombal.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Gombal : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@Gombal)
            modules(
                listOf(
                    commonModule,
                    localModule,
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}