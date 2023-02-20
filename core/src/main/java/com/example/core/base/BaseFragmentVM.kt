package com.example.core.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.core.utils.PageMessageUtil
import com.example.core.utils.ext.forceCloseApp

@Suppress("unused")
abstract class BaseFragmentVM<T: ViewBinding, A: BaseViewModel>(bindingFactory: (View) -> T): BaseFragment<T>(bindingFactory) {

    /**
     * Initialize current model on fragment
     */
    abstract val viewModel: A

    override fun onBeforeViewCreated() {
        super.onBeforeViewCreated()

        observeViewModel(viewModel)
    }

    /**
     * handle global event from BaseViewModel.
     * 1. show message
     * 2. handle token expired from remote data
     */
    open fun observeViewModel(viewModel: A) {
        viewModel.apply {
            eventMessage.observe(viewLifecycleOwner) { msg ->
                getMessageUtil(requireContext())?.showMessage(
                    messageType,
                    msg
                )
            }

            eventRestart.observe(viewLifecycleOwner) { result -> if (result) getBaseActivity()?.forceCloseApp() }
        }
    }

    /**
     * show/hide progress dialog
     * @param isShow param show/hide
     */
    open fun showProgressDialog(isShow: Boolean) = getBaseActivity()?.showProgressDialog(isShow)

    private fun getBaseActivity() = activity?.let { if (it is BaseActivityVM<*, *>) it else null }
}