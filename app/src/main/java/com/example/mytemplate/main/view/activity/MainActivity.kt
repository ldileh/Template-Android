package com.example.mytemplate.main.view.activity

import android.os.Bundle
import com.example.mytemplate.R
import com.example.mytemplate.base.ResponseApi
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.main.model.pojo.DefaultItemList
import com.example.mytemplate.main.presenter.MainPresenter
import com.example.mytemplate.main.view.adapter.ExampleAdapterListView
import com.example.mytemplate.main.view.dialog.CustomProgressDialog
import com.ldileh.imagedownloadmanager.ImageDownloader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val presenter: MainPresenter by lazy {
        MainPresenter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // example call api
        callApi()
    }

    private fun callApi() {
        CustomProgressDialog.showDialog(this@MainActivity)
        presenter.callUsers("mojombo", object : ResponseApi<List<DefaultItemList>>{

            override fun successApi(result: List<DefaultItemList>?) {
                CustomProgressDialog.closeDialog()

                configureListView(result ?: mutableListOf())
            }

            override fun failedApi(message: String) {
                CustomProgressDialog.closeDialog()
            }
        })
    }

    private fun configureListView(mItems: List<DefaultItemList>) {
        list?.adapter = ExampleAdapterListView(mItems)
    }
}