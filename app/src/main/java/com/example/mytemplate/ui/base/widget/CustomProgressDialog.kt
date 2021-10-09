package com.example.mytemplate.ui.base.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.mytemplate.R
import com.example.mytemplate.utils.ext.logError
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
            logError(e.message ?: "error while ${if (isShow) "show" else "hide"} dialog")
        }
    }
}