package com.example.mytemplate.ui.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.BaseActivityVM
import com.example.core.utils.PageMessageUtil
import com.example.core.utils.ext.PermissionsCameraResult
import com.example.core.utils.ext.registerPermissionsCamera
import com.example.core.utils.ext.runningPermissionCamera
import com.example.mytemplate.databinding.ActivityMainBinding
import com.example.mytemplate.domain.local.model.DefaultItemList
import com.example.mytemplate.ui.adapter.ExampleAdapterListView
import com.example.mytemplate.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivityVM<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate),
    PermissionsCameraResult{

    override val viewModel: MainViewModel by viewModels()

    override var messageType: PageMessageUtil.Type = PageMessageUtil.Type.SNACK_BAR

    override fun ActivityMainBinding.onViewCreated(savedInstanceState: Bundle?) {
        // example call api
        callApi()

        runningPermissionCamera(registerPermissionsCamera(this@MainActivity, this@MainActivity))

        viewRefresh.setOnRefreshListener { callApi() }
    }

    override fun observeViewModel(viewModel: MainViewModel) {
        super.observeViewModel(viewModel.apply {
            eventLoadUserRepo.observe(this@MainActivity) {
                binding.viewRefresh.isRefreshing = it
            }

            userRepoResult.observe(this@MainActivity) { result ->
                binding.configureListView(result ?: mutableListOf())
            }
        })
    }

    override fun onFinishPermissionCamera(result: Map<String, Boolean>, isAllGranted: Boolean) {
        Timber
            .tag(this@MainActivity.javaClass.simpleName)
            .e("onFinishPermissionCamera: $result, $isAllGranted")
    }

    private fun callApi() {
        viewModel.getUserRepo("mojombo")
    }

    private fun ActivityMainBinding.configureListView(mItems: List<DefaultItemList>) {
        list.adapter = ExampleAdapterListView(mItems){
            getMessageUtil(this@MainActivity)?.showMessage(
                messageType,
                "Hello World"
            )
        }
    }
}