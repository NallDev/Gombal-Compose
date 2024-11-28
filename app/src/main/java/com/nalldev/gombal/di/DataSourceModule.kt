package com.nalldev.gombal.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.nalldev.gombal.data.local.LocalDataSource
import com.nalldev.gombal.data.local.datastore.PreferenceDataSource
import com.nalldev.gombal.data.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun providePreferenceDatastore(dataStore: DataStore<Preferences>, ioDispatcher: CoroutineDispatcher): PreferenceDataSource = PreferenceDataSource(dataStore, ioDispatcher)

val dataSourceModule = module {
    single { providePreferenceDatastore(get(), get(named("IODispatcher"))) }

    single { LocalDataSource(get(), get(named("IODispatcher"))) }
    single { NetworkDataSource(get(), get(named("IODispatcher"))) }
}