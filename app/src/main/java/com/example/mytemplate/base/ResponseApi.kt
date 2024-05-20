package com.example.mytemplate.base

interface ResponseApi<T> {
    fun successApi(result: T?)
    fun failedApi(message: String)
}