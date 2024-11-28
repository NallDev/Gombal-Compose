package com.nalldev.gombal.data.repositories

import com.nalldev.gombal.data.local.LocalDataSource
import com.nalldev.gombal.data.network.NetworkDataSource
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.repositories.JobRepository
import com.nalldev.gombal.utils.JobMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class JobRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : JobRepository {

    override fun getJobs(): Flow<List<JobModel>> =
        combine(
            networkDataSource.fetchJobs(),
            localDataSource.getBookmarkedJobs()
        ) { networkJobs, jobBookmarks ->
            networkJobs.map { jobEntity ->
                val isBookmarked = jobBookmarks.any { it.id == jobEntity.slug }
                JobMapper.toDomain(jobEntity).copy(isBookmarked = isBookmarked)
            }
        }

    override suspend fun insertToBookmarked(job: JobModel) {
        localDataSource.insertToBookmarked(JobMapper.toJobBookmarkedData(job))
    }

    override suspend fun deleteFromBookmarked(job: JobModel) {
        localDataSource.deleteFromBookmarked(JobMapper.toJobBookmarkedData(job).id)
    }
}