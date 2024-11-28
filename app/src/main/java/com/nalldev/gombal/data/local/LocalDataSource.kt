package com.nalldev.gombal.data.local

import com.nalldev.gombal.data.local.room.dao.JobBookmarkedDao
import com.nalldev.gombal.data.local.room.model.JobBookmarkedEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(
    private val jobBookmarkedDao: JobBookmarkedDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getBookmarkedJobs() : Flow<List<JobBookmarkedEntity>> = jobBookmarkedDao.getJobs()

    suspend fun insertToBookmarked(job: JobBookmarkedEntity) = withContext(ioDispatcher) {
        jobBookmarkedDao.insertJob(job)
    }

    suspend fun deleteFromBookmarked(id: String) = withContext(ioDispatcher) {
        jobBookmarkedDao.deleteJob(id)
    }
}