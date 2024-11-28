package com.nalldev.gombal.data.network

import com.nalldev.gombal.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiServices(private val client : HttpClient) {
    suspend fun fetchJobs() = client.get(String.format("${BuildConfig.BASE_URL}/job-board-api"))
}