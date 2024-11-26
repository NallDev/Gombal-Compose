package com.nalldev.gombal.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nalldev.gombal.data.local.datastore.PreferenceDataSource
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "configuration")

fun provideDataStore(context: Context): DataStore<Preferences> = context.dataStore

fun providePreferenceDatastore(dataStore: DataStore<Preferences>, ioDispatcher: CoroutineDispatcher): PreferenceDataSource = PreferenceDataSource(dataStore, ioDispatcher)

val dataSourceModule = module {
    single { provideDataStore(get()) }
    single { providePreferenceDatastore(get(), get(named("IODispatcher"))) }
}