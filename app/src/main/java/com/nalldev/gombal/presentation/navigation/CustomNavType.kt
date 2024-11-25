package com.nalldev.gombal.presentation.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val UserType = object : NavType<User>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: User) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): User {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, User::class.java) as User
        } else {
            bundle.getParcelable<User>(key) as User
        }
    }

    override fun serializeAsValue(value: User): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString<User>(value)
    }
}