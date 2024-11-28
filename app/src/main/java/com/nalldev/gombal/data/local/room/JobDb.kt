package com.nalldev.gombal.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nalldev.gombal.data.local.room.dao.JobBookmarkedDao
import com.nalldev.gombal.data.local.room.model.JobBookmarkedEntity
import com.nalldev.gombal.utils.Converters

@Database(entities = [JobBookmarkedEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class JobDb : RoomDatabase() {
    abstract fun jobBookmarkedDao(): JobBookmarkedDao
}