package com.example.mytemplate.ui.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.BaseActivityVM
import com.example.core.utils.PageMessageUtil
import com.example.mytemplate.data.local.model.DefaultItemList
import com.example.mytemplate.databinding.ActivityMainBinding
import com.example.mytemplate.ui.adapter.ExampleAdapterListView
import com.example.mytemplate.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityVM<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private val mViewModel: MainViewModel by viewModels()

    override var messageType: PageMessageUtil.Type = PageMessageUtil.Type.SNACK_BAR

    override fun toolbarId(): Int? = null

    override fun ActivityMainBinding.onViewCreated(savedInstanceState: Bundle?) {
        // example call api
        callApi()

        viewRefresh.setOnRefreshListener { callApi() }
    }

    override fun viewModelObserver(vm: MainViewModel) {
        super.viewModelObserver(vm.apply {
            eventLoadUserRepo.observe(this@MainActivity, { binding.viewRefresh.isRefreshing = it })

            userRepoResult.observe(this@MainActivity, { result ->
                binding.configureListView(result ?: mutableListOf())
            })
        })
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = mViewModel
    }

    private fun callApi() {
        mViewModel.getUserRepo("mojombo")
    }

    private fun ActivityMainBinding.configureListView(mItems: List<DefaultItemList>) {
        list.adapter = ExampleAdapterListView(mItems)
    }
}