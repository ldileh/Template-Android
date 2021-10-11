package com.example.core.base

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.core.component.dialog.CustomProgressDialog
import com.example.core.utils.ext.forceCloseApp
import com.example.core.utils.ext.showToast

abstract class BaseActivity<T: ViewBinding, A: BaseViewModel>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    /** use this attribute to show progress dialog */
    val progressDialog: CustomProgressDialog by lazy {
        CustomProgressDialog(this)
    }

    protected var viewModel: A? = null

    lateinit var binding: T

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    /**
     * this method used for initialize current pattern on app.
     * @see AppCompatActivity.onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel?.let { vm -> viewModelObserver(vm) }

        configureToolbar()
        binding.onViewCreated(savedInstanceState)
    }

    /**
     * init toolbar resource id if exist
     */
    @IdRes
    abstract fun toolbarId(): Int?

    /**
     * set event of view after prepare all condition on page
     * @param savedInstanceState: state of page
     * @see AppCompatActivity.onCreate
     */
    abstract fun T.onViewCreated(savedInstanceState: Bundle?)

    /**
     * handle global event from BaseViewModel.
     * 1. show message
     * 2. handle token expired from remote data
     *
     * @param vm view model on current page
     */
    open fun viewModelObserver(vm: A) {
        vm.apply {
            eventMessage.observe(this@BaseActivity, { msg -> this@BaseActivity.showToast(msg) })

            eventRestart.observe(this@BaseActivity, { result ->
                if (result)
                    this@BaseActivity.forceCloseApp()
            })
        }
    }

    /**
     * Initialize current model on activity
     */
    open fun initViewModel(){ }

    /**
     * Configure current toolbar if exist
     */
    private fun configureToolbar(){
        toolbarId()?.apply {
            setSupportActionBar(findViewById(this))
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}