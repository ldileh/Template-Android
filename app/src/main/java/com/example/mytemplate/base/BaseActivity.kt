package com.example.mytemplate.base

import android.annotation.SuppressLint
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.example.mytemplate.main.view.dialog.CustomProgressDialog

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    val progressDialog: CustomProgressDialog by lazy {
        CustomProgressDialog(this)
    }

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }
}