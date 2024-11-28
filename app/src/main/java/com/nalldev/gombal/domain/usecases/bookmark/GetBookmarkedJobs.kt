package com.nalldev.gombal.domain.usecases.bookmark

import com.nalldev.gombal.domain.repositories.BookmarkRepository


class GetBookmarkedJobs(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke() = bookmarkRepository.getBookmarkedJobs()
}