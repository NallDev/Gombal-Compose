package com.nalldev.gombal.domain.usecases.dark_mode

import com.nalldev.gombal.domain.repositories.DarkModeRepository


class IsDarkMode(
    private val darkModeRepository: DarkModeRepository
) {
    operator fun invoke() = darkModeRepository.isDarkMode
}