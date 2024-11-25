package com.nalldev.gombal.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val id : Int,
    val name : String
) : Parcelable
