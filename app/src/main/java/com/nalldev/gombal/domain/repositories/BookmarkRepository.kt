package com.nalldev.gombal.domain.repositories

import com.nalldev.gombal.domain.model.JobModel
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedJobs(): Flow<List<JobModel>>

    suspend fun deleteFromBookmarked(job: JobModel)
}