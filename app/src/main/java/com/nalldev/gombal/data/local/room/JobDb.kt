package com.nalldev.gombal.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nalldev.gombal.data.local.room.dao.JobFavoritesDao
import com.nalldev.gombal.data.local.room.model.JobFavoritesEntity
import com.nalldev.gombal.utils.Converters

@Database(entities = [JobFavoritesEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class JobDb : RoomDatabase() {
    abstract fun jobFavoritesDao(): JobFavoritesDao
}