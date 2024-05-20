package com.example.mytemplate.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.mytemplate.widget.CustomProgressDialog

@SuppressLint("Registered")
abstract class BaseActivity<T: ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    val progressDialog: CustomProgressDialog by lazy {
        CustomProgressDialog(this)
    }

    lateinit var binding: T

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        configureToolbar()
        onViewCreated(savedInstanceState)
    }

    @IdRes
    abstract fun toolbarId(): Int?

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    private fun configureToolbar(){
        toolbarId()?.let {
            setSupportActionBar(findViewById(it))
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}