package com.example.mytemplate.config

import com.example.mytemplate.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    @Suppress("SameParameterValue")
    private fun httpClientBuilder(isDebug: Boolean) = OkHttpClient.Builder().apply {
        if(isDebug)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }.build()

    fun getRetrofitBuilder(): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder(GlobalConfig.IS_DEBUG))
            .build()
}