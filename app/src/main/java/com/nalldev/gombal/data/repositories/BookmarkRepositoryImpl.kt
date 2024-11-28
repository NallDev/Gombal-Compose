package com.nalldev.gombal.data.repositories

import com.nalldev.gombal.data.local.LocalDataSource
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.repositories.BookmarkRepository
import com.nalldev.gombal.utils.JobMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkRepositoryImpl(
    private val localDataSource: LocalDataSource
) : BookmarkRepository {
    override fun getBookmarkedJobs(): Flow<List<JobModel>> = localDataSource.getBookmarkedJobs().map {
        it.map { entity ->
            JobMapper.localToDomain(entity)
        }
    }

    override suspend fun deleteFromBookmarked(job: JobModel) {
        localDataSource.deleteFromBookmarked(job.id)
    }
}