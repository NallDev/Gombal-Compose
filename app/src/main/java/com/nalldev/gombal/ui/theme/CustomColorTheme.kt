package com.nalldev.gombal.ui.theme

import androidx.compose.ui.graphics.Color

object CustomColorTheme {
    private val lightCustomColor = CustomColor(
        colorPrimary = Color(0xFF0D988C),
        colorBackground = Color(0xFFFFFFFF),
        colorOnBackground = Color(0xFF182420),
        colorBackground80 = Color(0xCCFFFFFF),
        colorBackground20 = Color(0x33FFFFFF),
        black = Color(0xFF182420),
        white = Color(0xFFFFFFFF),
        gray = Color(0xFFEDECEB),
        subtitle = Color(0xFFBAB8B4),
        onBoardingBackground = Color(0xFFE7F5F3)
    )

    private val darkCustomColor = CustomColor(
        colorPrimary = Color(0xFF0D988C),
        colorBackground = Color(0xFF241F18),
        colorOnBackground = Color(0xFFFFFFFF),
        colorBackground80 = Color(0xCC241F18),
        colorBackground20 = Color(0x33241F18),
        black = Color(0xFF182420),
        white = Color(0xFFFFFFFF),
        gray = Color(0xFF3E3933),
        subtitle = Color(0xFF97948F),
        onBoardingBackground = Color(0xFFE7F5F3)
    )

    fun colorPrimary(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.colorPrimary else lightCustomColor.colorPrimary
    fun colorBackground(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.colorBackground else lightCustomColor.colorBackground
    fun colorOnBackground(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.colorOnBackground else lightCustomColor.colorOnBackground
    fun colorBackground80(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.colorBackground80 else lightCustomColor.colorBackground80
    fun colorBackground20(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.colorBackground20 else lightCustomColor.colorBackground20
    fun black(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.black else lightCustomColor.black
    fun white(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.white else lightCustomColor.white
    fun gray(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.gray else lightCustomColor.gray
    fun subtitle(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.subtitle else lightCustomColor.subtitle
    fun onBoardingBackground(isDarkMode: Boolean) : Color = if (isDarkMode) darkCustomColor.onBoardingBackground else lightCustomColor.onBoardingBackground
}