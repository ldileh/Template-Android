package com.example.mytemplate.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.mytemplate.main.view.activity.LoginActivity
import retrofit2.Response

fun <T> Response<T>?.checkResponse(context: Context, responseFailed: (message: String) -> Unit): T? = when{

    this == null -> {
        responseFailed("Something went wrong")
        null
    }

    !isSuccessful -> {
        when(code()){
            401 -> { // failed authentication
                SessionManager(context).clearSession{
                    if(context is Activity){
                        context.apply {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finishAffinity()
                        }
                    }
                }

                responseFailed(message())
            }
            else -> responseFailed(message())
        }

        null
    }

    body() == null -> {
        responseFailed("Response is empty")
        null
    }

    else -> body()
}