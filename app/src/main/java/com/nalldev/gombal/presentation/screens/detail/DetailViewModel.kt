package com.nalldev.gombal.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.nalldev.gombal.domain.model.JobModel
import com.nalldev.gombal.domain.usecases.job.JobUseCases
import com.nalldev.gombal.presentation.navigation.JobType
import com.nalldev.gombal.presentation.navigation.Screen
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val jobUseCases: JobUseCases
) : ViewModel() {

    var jobModel by mutableStateOf(savedStateHandle.toRoute<Screen.Detail>(typeMap = mapOf(typeOf<JobModel>() to JobType)).jobModel)
        private set

    var isBookmarked by mutableStateOf(jobModel.isBookmarked)
        private set

    fun updateBookmarkedStatus(job: JobModel, isBookmarked: Boolean) = viewModelScope.launch {
        if (isBookmarked) {
            jobUseCases.insertJobToBookmarked(job)
            this@DetailViewModel.isBookmarked = true
        } else {
            jobUseCases.deleteJobFromBookmarked(job)
            this@DetailViewModel.isBookmarked = false
        }
    }
}