package com.nalldev.gombal.di

import com.nalldev.gombal.BuildConfig
import com.nalldev.gombal.data.network.api.ApiServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

fun provideHttpClient(okHttpClient: OkHttpClient) : HttpClient {
    return HttpClient(OkHttp) {
        engine {
            preconfigured = okHttpClient
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                }
            )
        }
    }
}

fun provideOkHttpClient(certificatePinner: CertificatePinner, loggingInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .certificatePinner(certificatePinner)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideCertificatePinner() : CertificatePinner {
    return CertificatePinner.Builder()
        .add(BuildConfig.HOSTNAME, "sha256/F+hE+4iqEOmeNBmtNBQ8d2fRGrFMpgbwZnsHS6bU4LU=")
        .add(BuildConfig.HOSTNAME, "sha256/yDu9og255NN5GEf+Bwa9rTrqFQ0EydZ0r1FCh9TdAW4=")
        .add(BuildConfig.HOSTNAME, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
        .build()
}

fun provideLoggingInterceptor() : Interceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

val networkModule = module {
    single { provideCertificatePinner() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideHttpClient(get()) }
    single { ApiServices(get()) }
}