package com.nalldev.gombal.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.nalldev.gombal.data.local.room.JobDb
import com.nalldev.gombal.data.local.room.dao.JobBookmarkedDao
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "configuration")

fun provideDatabase(application: Application): JobDb =
    Room.databaseBuilder(
        application,
        JobDb::class.java,
        "job_db"
    )
        .fallbackToDestructiveMigration()
        .build()

fun provideJobBookmarkedDao(jobDb: JobDb): JobBookmarkedDao = jobDb.jobBookmarkedDao()

fun provideDataStore(context: Context): DataStore<Preferences> = context.dataStore

val localModule = module {
    single { provideDatabase(get()) }
    single { provideJobBookmarkedDao(get()) }

    single { provideDataStore(get()) }
}