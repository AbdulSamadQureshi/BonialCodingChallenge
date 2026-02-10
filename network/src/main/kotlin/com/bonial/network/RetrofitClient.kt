package com.bonial.network

import com.bonial.core.preferences.PreferenceKeys
import com.bonial.core.preferences.SharedPrefsManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val baseUrl: String,
    private val enableLogging: Boolean,
    private val sharedPrefsManager: SharedPrefsManager
) {
    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (enableLogging) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Platform", "Android")

                sharedPrefsManager.getStringValue(PreferenceKeys.KEY_ACCESS_TOKEN, "")?.let { token ->
                    if (token.isNotEmpty()) {
                        requestBuilder.header("Authorization", "Bearer $token")
                    }
                }

                val lang = sharedPrefsManager.getStringValue(PreferenceKeys.KEY_LANGUAGE, "en") ?: "en"
                requestBuilder.header("Accept-Language", lang)

                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
