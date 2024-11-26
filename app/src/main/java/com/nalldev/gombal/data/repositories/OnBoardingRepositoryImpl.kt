package com.nalldev.gombal.data.repositories

import com.nalldev.gombal.data.local.datastore.PreferenceDataSource
import com.nalldev.gombal.domain.repositories.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class OnBoardingRepositoryImpl(
    private val preferenceDataSource: PreferenceDataSource
) : OnBoardingRepository {
    override val isOnBoardingFinished: Flow<Boolean> = preferenceDataSource.isOnBoardingFinished

    override suspend fun setIsOnBoardingFinished(isOnBoardingFinished: Boolean) {
        preferenceDataSource.setIsOnBoardingFinished(isOnBoardingFinished)
    }
}