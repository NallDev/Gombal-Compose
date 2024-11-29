package com.nalldev.gombal.presentation.screens.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.usecases.dark_mode.DarkModeUseCases
import com.nalldev.gombal.domain.usecases.job.JobUseCases
import com.nalldev.gombal.utils.RemoteHelper
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val application: Application,
    private val jobUseCases: JobUseCases,
    private val darkModeUseCases: DarkModeUseCases,
) : ViewModel() {
    var jobs by mutableStateOf<List<JobModel>>(emptyList())
        private set

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.onStart { fetchJobs() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = false
    )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    var toastEvent by mutableStateOf<String?>(null)
        private set

    @OptIn(FlowPreview::class)
    fun fetchJobs() {
        combine(
            jobUseCases.getJobs(),
            _searchQuery.debounce(300)
        ) { jobs, query ->
            if (query.isBlank()) {
                jobs
            } else {
                jobs.filter { job ->
                    job.title.contains(query, ignoreCase = true) ||
                            job.companyName.contains(query, ignoreCase = true) ||
                            job.location.contains(query, ignoreCase = true)
                }
            }
        }
            .onStart {
                _loading.update { true }
            }
            .onEach { filteredJobs ->
                _loading.update { false }
                jobs = filteredJobs
            }
            .catch { throwable ->
                _loading.update { false }
                val message = when (throwable) {
                    is IOException -> RemoteHelper.noInternetMessage(application)
                    is ClientRequestException -> RemoteHelper.remoteErrorMessage(
                        application,
                        throwable.response.status.value
                    )

                    else -> RemoteHelper.errorDefaultMessage(application)
                }
                toastEvent = message
            }
            .launchIn(viewModelScope)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    fun updateBookmarkedStatus(job: JobModel, isBookmarked: Boolean) = viewModelScope.launch {
        if (isBookmarked) {
            jobUseCases.insertJobToBookmarked(job)
        } else {
            jobUseCases.deleteJobFromBookmarked(job)
        }
    }

    fun setDarkMode(isDarkMode: Boolean) = viewModelScope.launch {
        darkModeUseCases.setDarkMode(isDarkMode)
    }

    fun clearToastEvent() {
        toastEvent = null
    }
}