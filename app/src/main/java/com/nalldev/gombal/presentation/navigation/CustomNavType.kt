package com.nalldev.gombal.presentation.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.nalldev.gombal.domain.model.JobModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val JobType = object : NavType<JobModel>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: JobModel) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): JobModel {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, JobModel::class.java) as JobModel
        } else {
            bundle.getParcelable<JobModel>(key) as JobModel
        }
    }

    override fun serializeAsValue(value: JobModel): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): JobModel {
        return Json.decodeFromString<JobModel>(value)
    }
}