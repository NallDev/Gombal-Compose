package com.nalldev.gombal.presentation.screens.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.usecases.bookmark.BookmarkedUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val bookmarkedUseCases: BookmarkedUseCases
) : ViewModel() {
    val bookmarkedJobs = bookmarkedUseCases.getBookmarkedJobs().catch {
        toastEvent = it.message.toString()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    var toastEvent by mutableStateOf<String?>(null)
        private set

    fun deleteFromBookmarked(job: JobModel) = viewModelScope.launch {
        bookmarkedUseCases.deleteJobFromBookmarked(job)
    }

    fun clearToastEvent() {
        toastEvent = null
    }
}