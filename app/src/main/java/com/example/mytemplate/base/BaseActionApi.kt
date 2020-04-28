package com.example.mytemplate.base

import android.content.Context
import com.example.mytemplate.utils.UserMessage
import retrofit2.Response

open class BaseActionApi(private val context: Context?) {

    protected fun checkResponseIsSuccess(response: Response<*>): Boolean = when{
        response.code() == 401 -> {
            UserMessage(context).defaultMessage("Problem with authentication!")
            false
        }

        response.code() >= 500 -> {
            UserMessage(context).defaultMessage("Something went wrong in server!")
            false
        }

        response.body() == null -> {
            UserMessage(context).defaultMessage("Response is empty")
            false
        }

        else -> true
    }
}