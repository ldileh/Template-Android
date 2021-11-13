package com.example.core.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.config.BaseConfig
import com.example.core.utils.PageMessageUtil
import com.example.core.utils.ext.forceCloseApp
import com.example.core.utils.ext.viewLifecycleLazy

@Suppress("unused")
abstract class BaseFragmentVM<T: ViewBinding, A: BaseViewModel>(private val bindingFactory: (View) -> T): Fragment() {

    /**
     * view model of page
     */
    protected var viewModel: A? = null

    /**
     * view binding of page
     */
    @Suppress
    val binding: T by viewLifecycleLazy{ bindingFactory(requireView()) }

    /**
     * default message type of page
     */
    open var messageType = BaseConfig.messageType

    /**
     * message util of page
     */
    private lateinit var messageUtil: PageMessageUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageUtil = PageMessageUtil(requireContext(), binding.root)

        initViewModel()
        viewModel?.let { vm -> viewModelObserver(vm) }
        binding.onViewCreated(savedInstanceState)
    }

    /**
     * set event of view after prepare all condition on page (fragment)
     * @param savedInstanceState state of page
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
            eventMessage.observe(viewLifecycleOwner, { msg -> messageUtil.showMessage(messageType, msg) })

            eventRestart.observe(viewLifecycleOwner, { result -> if (result) getBaseActivity()?.forceCloseApp() })
        }
    }

    /**
     * Initialize current model on fragment
     */
    open fun initViewModel(){ }

    /**
     * show/hide progress dialog
     * @param isShow param show/hide
     */
    open fun showProgressDialog(isShow: Boolean) = getBaseActivity()?.showProgressDialog(isShow)

    private fun getBaseActivity() = activity?.let { if (it is BaseActivityVM<*, *>) it else null }
}