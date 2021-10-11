package com.example.mytemplate.base

import android.content.Context
import com.example.mytemplate.utils.ResultRepository

@Suppress("unused")
abstract class BaseUseCase(private val context: Context){

    suspend fun <T> handleResponse(call: suspend () -> ResultRepository<T>): ResultRepository<T> = call()
}