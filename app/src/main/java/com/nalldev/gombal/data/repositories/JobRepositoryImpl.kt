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
            localDataSource.getFavoriteJobs()
        ) { networkJobs, favoriteJobs ->
            networkJobs.map { jobEntity ->
                val isFavorite = favoriteJobs.any { it.id == jobEntity.slug }
                JobMapper.toDomain(jobEntity).copy(isFavorite = isFavorite)
            }
        }

    override suspend fun insertToFavorite(job: JobModel) {
        localDataSource.insertToFavorite(JobMapper.toJobFavoritesData(job))
    }

    override suspend fun deleteFromFavorite(job: JobModel) {
        localDataSource.deleteFromFavorite(JobMapper.toJobFavoritesData(job).id)
    }
}