package com.example.core.base

import android.content.Context
import com.example.core.utils.Resource

@Suppress("unused")
abstract class BaseUseCase(private val context: Context){

    suspend fun <T> handleResponse(call: suspend () -> Resource<T>): Resource<T> = call()
}