package com.example.mytemplate.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.mytemplate.R
import java.lang.Exception

class CustomProgressDialog(context: Context) : Dialog(context) {

    init {
        // dialog configuration
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
    }

    fun showDialog(isShow: Boolean) {
        try {
            if (isShow){
                if (!isShowing) show()
            }else{
                if (isShowing) dismiss()
            }

        }catch (e: Exception){
            Log.e(CustomProgressDialog::class.java.simpleName, e.message ?: "error while ${if (isShow) "show" else "hide"} dialog")
        }
    }
}