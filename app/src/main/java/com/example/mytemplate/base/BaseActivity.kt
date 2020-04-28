package com.example.mytemplate.base

import android.annotation.SuppressLint
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }
}