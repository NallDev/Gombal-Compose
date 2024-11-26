package com.nalldev.gombal.utils

import android.app.UiModeManager
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonHelper {
    fun convertTimestampToDate(timestamp: Int?): String {
        if (timestamp == null) return "-"
        val date = Date(timestamp.toLong() * 1000L)
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return format.format(date)
    }

    fun setDarkMode(context: Context, isDarkMode: Boolean) {
        val uiModeManager =
            context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        uiModeManager.setApplicationNightMode(
            if (isDarkMode) UiModeManager.MODE_NIGHT_YES else UiModeManager.MODE_NIGHT_NO
        )
    }
}