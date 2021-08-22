package com.example.mytemplate.view.activity

import android.os.Bundle
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.base.ResponseApi
import com.example.mytemplate.databinding.ActivityMainBinding
import com.example.mytemplate.data.local.model.DefaultItemList
import com.example.mytemplate.presenter.MainPresenter
import com.example.mytemplate.view.adapter.ExampleAdapterListView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val presenter: MainPresenter by lazy {
        MainPresenter(this@MainActivity)
    }

    override fun toolbarId(): Int? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        // example call api
        callApi()
    }

    private fun callApi() {
        progressDialog.showDialog(true)

        presenter.callUsers("mojombo", object : ResponseApi<List<DefaultItemList>>{

            override fun successApi(result: List<DefaultItemList>?) {
                progressDialog.showDialog(false)

                configureListView(result ?: mutableListOf())
            }

            override fun failedApi(message: String) {
                progressDialog.showDialog(false)
            }
        })
    }

    private fun configureListView(mItems: List<DefaultItemList>) {
        binding.list.adapter = ExampleAdapterListView(mItems)
    }
}