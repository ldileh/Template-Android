package com.example.mytemplate.base

import android.content.Context
import com.example.mytemplate.config.RetrofitConfig

open class BaseApi<T>(private val context: Context, private val serviceClass: Class<T>) {

    protected fun createService(): T = RetrofitConfig
            .getRetrofitBuilder(RetrofitConfig.baseUrl)
            .create(serviceClass)
}