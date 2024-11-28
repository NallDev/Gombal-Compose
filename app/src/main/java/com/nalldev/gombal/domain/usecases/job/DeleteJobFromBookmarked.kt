package com.nalldev.gombal.domain.usecases.job

import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.repositories.JobRepository

class DeleteJobFromBookmarked(
    private val repository: JobRepository
) {
    suspend operator fun invoke(job : JobModel) = repository.deleteFromBookmarked(job)
}