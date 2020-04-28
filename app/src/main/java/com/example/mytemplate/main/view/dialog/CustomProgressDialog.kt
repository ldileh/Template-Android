package com.example.mytemplate.main.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.mytemplate.R
import java.lang.Exception

class CustomProgressDialog private constructor(context: Context) : Dialog(context) {

    companion object {
        private val TAG = CustomProgressDialog::class.java.simpleName

        private var dialog: CustomProgressDialog? = null

        fun showDialog(context: Context?) {
            if (dialog != null && dialog!!.isShowing) return

            try {
                context?.let {
                    dialog = CustomProgressDialog(context)
                    dialog!!.show()
                }
            }catch (e: Exception){
                Log.e(TAG, e.message ?: "showDialog: error while show dialog")
            }
        }

        fun closeDialog() {
            try {
                dialog?.let {
                    if(it.isShowing)
                        it.dismiss()
                }

                // remove dialog from memory
                dialog = null
            }catch (e: Exception){
                Log.e(TAG, e.message ?: "showDialog: error while close dialog")
            }
        }
    }

    init {
        // dialog configuration
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
    }
}