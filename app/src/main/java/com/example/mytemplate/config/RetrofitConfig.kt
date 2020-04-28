package com.example.mytemplate.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    val baseUrl = GlobalConfig.baseUrl

    private fun httpClientBuilder(isDebug: Boolean): OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        if(isDebug) httpClient.addInterceptor(logging)

        return httpClient.build()
    }

    fun getRetrofitBuilder(baseUrl: String): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder(GlobalConfig.isDebug))
            .build()
}