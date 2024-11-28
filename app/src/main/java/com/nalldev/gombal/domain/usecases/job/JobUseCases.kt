package com.nalldev.gombal.domain.usecases.job

data class JobUseCases(
    val getJobs: GetJobs,
    val insertJobToFavorite: InsertJobToFavorite,
    val deleteJobFromFavorite: DeleteJobFromFavorite
)
