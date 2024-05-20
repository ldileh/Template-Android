package com.example.mytemplate.utils

import android.content.Context
import android.widget.Toast

class UserMessage(private val context: Context?) {

    fun defaultMessage(message: String?) {
        context?.let {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}