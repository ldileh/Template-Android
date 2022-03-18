package com.example.mytemplate.ui.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.BaseActivityVM
import com.example.core.utils.PageMessageUtil
import com.example.mytemplate.databinding.ActivityMainBinding
import com.example.mytemplate.domain.local.model.DefaultItemList
import com.example.mytemplate.ui.adapter.ExampleAdapterListView
import com.example.mytemplate.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityVM<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override var messageType: PageMessageUtil.Type = PageMessageUtil.Type.SNACK_BAR

    override fun ActivityMainBinding.onViewCreated(savedInstanceState: Bundle?) {
        // example call api
        callApi()

        viewRefresh.setOnRefreshListener { callApi() }
    }

    override fun MainViewModel.observeViewModel() = apply {
        eventLoadUserRepo.observe(this@MainActivity) { binding.viewRefresh.isRefreshing = it }

        userRepoResult.observe(this@MainActivity) { result ->
            binding.configureListView(result ?: mutableListOf())
        }
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