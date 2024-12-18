package com.nalldev.gombal.domain.usecases.job

data class JobUseCases(
    val getJobs: GetJobs,
    val insertJobToBookmarked: InsertJobToBookmarked,
    val deleteJobFromBookmarked: DeleteJobFromBookmarked
)
