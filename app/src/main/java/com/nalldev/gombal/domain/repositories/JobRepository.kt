package com.nalldev.gombal.domain.repositories

import com.nalldev.gombal.domain.model.JobModel
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    fun getJobs(): Flow<List<JobModel>>
    suspend fun insertToFavorite(job: JobModel)
    suspend fun deleteFromFavorite(job: JobModel)
}