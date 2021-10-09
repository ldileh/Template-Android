package com.example.mytemplate.ui.main.view.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.data.local.model.DefaultItemList
import com.example.mytemplate.databinding.ActivityMainBinding
import com.example.mytemplate.ui.main.adapter.ExampleAdapterListView
import com.example.mytemplate.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private val mViewModel: MainViewModel by viewModels()

    override fun toolbarId(): Int? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        // example call api
        callApi()
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

    override fun ActivityMainBinding.initViewBinding() {
        apply {
            viewRefresh.setOnRefreshListener {
                mViewModel.getUserRepo("mojombo")
            }
        }
    }

    private fun callApi() {
        progressDialog.showDialog(true)

        mViewModel.getUserRepo("mojombo")
    }

    private fun ActivityMainBinding.configureListView(mItems: List<DefaultItemList>) {
        list.adapter = ExampleAdapterListView(mItems)
    }
}