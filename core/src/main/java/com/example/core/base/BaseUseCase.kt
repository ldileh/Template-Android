package com.example.core.base

import android.content.Context
import com.example.core.utils.ResultRepository

@Suppress("unused")
abstract class BaseUseCase(private val context: Context){

    suspend fun <T> handleResponse(call: suspend () -> ResultRepository<T>): ResultRepository<T> = call()
}