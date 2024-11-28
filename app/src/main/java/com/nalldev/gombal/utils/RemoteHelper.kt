package com.nalldev.gombal.utils

import android.content.Context
import com.nalldev.gombal.R

object RemoteHelper {
    fun remoteErrorMessage(context: Context, code: Int): String {
        return when (code) {
            400 -> context.getString(R.string.error_code_400)
            401 -> context.getString(R.string.error_code_401)
            403 -> context.getString(R.string.error_code_403)
            404 -> context.getString(R.string.error_code_404)
            408 -> context.getString(R.string.error_code_408)
            429 -> context.getString(R.string.error_code_429)
            in 500..599 -> context.getString(R.string.error_code_500)
            else -> context.getString(R.string.error_code_default)
        }
    }

    fun errorDefaultMessage(context: Context): String {
        return context.getString(R.string.error_code_default)
    }

    fun noInternetMessage(context: Context): String {
        return context.getString(R.string.check_internet_connection)
    }
}