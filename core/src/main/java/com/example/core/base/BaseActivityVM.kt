package com.example.core.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.example.core.utils.ext.forceCloseApp

abstract class BaseActivityVM<T: ViewBinding, A: BaseViewModel>(bindingFactory: (LayoutInflater) -> T) : BaseActivity<T>(bindingFactory) {

    abstract val viewModel: A

    override fun onBeforeViewCreated() {
        super.onBeforeViewCreated()

        viewModel.observeViewModel()
    }

    /**
     * handle global event from BaseViewModel.
     * 1. show message
     * 2. handle token expired from remote data
     */
    open fun A.observeViewModel() = apply {
        eventMessage.observe(this@BaseActivityVM) { msg ->
            getMessageUtil(this@BaseActivityVM)?.showMessage(
                messageType,
                msg
            )
        }

        eventRestart.observe(this@BaseActivityVM) { result -> if (result) this@BaseActivityVM.forceCloseApp() }
    }
}