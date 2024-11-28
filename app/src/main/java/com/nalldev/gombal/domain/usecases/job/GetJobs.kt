package com.nalldev.gombal.domain.usecases.job

import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.repositories.JobRepository
import kotlinx.coroutines.flow.Flow

class GetJobs(
    private val repository: JobRepository
) {
    operator fun invoke() : Flow<List<JobModel>> = repository.getJobs()
}