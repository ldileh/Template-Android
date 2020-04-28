package com.example.mytemplate.base

import retrofit2.Response

interface ResponseApi<T> {
    fun successApi(response: Response<T>)
    fun failedApi(response: Response<T>?, message: String)
}