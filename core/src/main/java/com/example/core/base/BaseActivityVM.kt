package com.example.core.base

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.core.component.dialog.CustomProgressDialog
import com.example.core.config.BaseConfig
import com.example.core.utils.PageMessageUtil
import com.example.core.utils.ext.forceCloseApp

abstract class BaseActivityVM<T: ViewBinding, A: BaseViewModel>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    /** use this attribute to show progress dialog */
    private val progressDialog: CustomProgressDialog by lazy { CustomProgressDialog(this) }

    /**
     * view model of page
     */
    protected var viewModel: A? = null

    /**
     * view binding of page
     */
    lateinit var binding: T

    /**
     * default message type of page
     */
    open var messageType = BaseConfig.messageType

    /**
     * message util of page
     */
    private lateinit var messageUtil: PageMessageUtil

    override fun onStart() {
        super.onStart()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
    }

    /**
     * initialize current pattern on app.
     * @see AppCompatActivity.onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel?.let { vm -> viewModelObserver(vm) }

        messageUtil = PageMessageUtil(this@BaseActivityVM, binding.root)

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
            eventMessage.observe(this@BaseActivityVM, { msg -> messageUtil.showMessage(messageType, msg) })

            eventRestart.observe(this@BaseActivityVM, { result -> if (result) this@BaseActivityVM.forceCloseApp() })
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

    /**
     * show/hide progress dialog
     * @param isShow param show/hide
     */
    fun showProgressDialog(isShow: Boolean) = progressDialog.showDialog(isShow)
}