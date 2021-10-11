package com.example.mytemplate.utils.ext

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.forceCloseApp(){
    showToast("Close app")

    try {
        finishAffinity()
    }catch (e: Exception){
        logError(msg = e.message, e = e)
    }
}

fun Context.forceCloseApp(){
    if (this is Activity){
        forceCloseApp()
    }
}

fun Context.showToast(msg: String?) = try {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}catch (e: Exception){
    logError(msg = e.message, e = e)
}
