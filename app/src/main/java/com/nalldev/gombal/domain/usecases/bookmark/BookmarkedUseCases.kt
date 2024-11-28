package com.nalldev.gombal.domain.usecases.bookmark

import com.nalldev.gombal.domain.usecases.job.DeleteJobFromBookmarked

data class BookmarkedUseCases(
    val getBookmarkedJobs: GetBookmarkedJobs,
    val deleteJobFromBookmarked: DeleteJobFromBookmarked
)