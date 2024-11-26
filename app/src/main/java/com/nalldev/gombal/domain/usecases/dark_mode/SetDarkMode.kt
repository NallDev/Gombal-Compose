package com.nalldev.gombal.domain.usecases.dark_mode

import com.nalldev.gombal.domain.repositories.DarkModeRepository

class SetDarkMode(
    private val darkModeRepository: DarkModeRepository
) {
    suspend operator fun invoke(darkMode: Boolean) = darkModeRepository.setIsDarkMode(darkMode)
}