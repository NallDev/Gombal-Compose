package com.nalldev.gombal.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nalldev.gombal.data.local.room.model.JobBookmarkedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobBookmarkedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobBookmarkedEntity)

    @Query("SELECT * FROM job_bookmarked")
    fun getJobs() : Flow<List<JobBookmarkedEntity>>

    @Query("DELETE FROM job_bookmarked WHERE id = :id")
    suspend fun deleteJob(id: String)

}