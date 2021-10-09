package com.example.mytemplate.base

import com.example.mytemplate.config.RetrofitConfig
import com.example.mytemplate.utils.ResultRepository
import com.example.mytemplate.utils.ext.logError
import retrofit2.Response

abstract class BaseService {

    companion object{

        fun <T> createService(serviceClass: Class<T>): T = RetrofitConfig
            .getRetrofitBuilder()
            .create(serviceClass)
    }

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultRepository<T> = try {
        val response = call()

        when{
            response.isSuccessful && response.body() != null ->
                ResultRepository.success(response.body())

            response.isSuccessful && response.body() == null ->
                error("Empty body response: ${response.message()}")

            else ->
                error(message = "${response.code()}: ${response.message()}", code = response.code())
        }
    } catch (e: Exception) {
        error(message = e.message ?: e.toString(), e = e)
    }

    private fun <T> error(message: String, code: Int? = null, e: Exception? = null): ResultRepository<T> {
        logError(message, e)

        return ResultRepository.error(
            msg = "Network call has failed for a following reason: $message",
            code = code
        )
    }
}