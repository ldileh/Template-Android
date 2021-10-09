package com.example.mytemplate.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.mytemplate.ui.base.widget.CustomProgressDialog

@SuppressLint("Registered")
abstract class BaseActivity<T: ViewBinding, A: BaseViewModel>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    val progressDialog: CustomProgressDialog by lazy {
        CustomProgressDialog(this)
    }

    protected var viewModel: A? = null

    lateinit var binding: T

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel?.let { vm -> viewModelObserver(vm) }
        binding.initViewBinding()

        configureToolbar()
        onViewCreated(savedInstanceState)
    }

    @IdRes
    abstract fun toolbarId(): Int?

    abstract fun onViewCreated(savedInstanceState: Bundle?)

    open fun viewModelObserver(vm: A) {
        vm.apply {
            eventMessage.observe(this@BaseActivity, { msg ->
                Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
            })
        }
    }

    open fun initViewModel(){ }

    abstract fun T.initViewBinding()

    private fun configureToolbar(){
        toolbarId()?.let {
            setSupportActionBar(findViewById(it))
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}