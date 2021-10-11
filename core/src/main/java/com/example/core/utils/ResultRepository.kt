package com.example.core.utils

data class ResultRepository<out T>(val status: Status, val data: T?, val message: String? = null, val code: Int? = null){

    enum class Status {
        SUCCESS,
        FAILED,
        LOADING
    }

    companion object{

        fun <T> success(data: T?): ResultRepository<T> {
            return ResultRepository(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null, code: Int? = null): ResultRepository<T> {
            return ResultRepository(Status.FAILED, data, msg, code)
        }

        @Suppress("unused")
        fun <T> loading(data: T? = null): ResultRepository<T> {
            return ResultRepository(Status.LOADING, data, null)
        }
    }
}