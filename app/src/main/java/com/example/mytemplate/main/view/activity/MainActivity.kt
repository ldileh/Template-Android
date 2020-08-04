package com.example.mytemplate.main.view.activity

import android.os.Bundle
import com.example.mytemplate.R
import com.example.mytemplate.base.ResponseApi
import com.example.mytemplate.api.GlobalApi
import com.example.mytemplate.base.BaseActivity
import com.example.mytemplate.main.model.api.GithubRepoResponseModel
import com.example.mytemplate.main.model.pojo.DefaultItemList
import com.example.mytemplate.main.view.adapter.ExampleAdapterListView
import com.example.mytemplate.main.view.dialog.CustomProgressDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // example call api
        callApi()
    }

    private fun callApi() {
        CustomProgressDialog.showDialog(this@MainActivity)
        GlobalApi(this@MainActivity).getUserRepo(object : ResponseApi<List<GithubRepoResponseModel>> {
            override fun successApi(response: Response<List<GithubRepoResponseModel>>) {
                CustomProgressDialog.closeDialog()

                val mItems: MutableList<DefaultItemList> = ArrayList()
                // parse response api
                response.body()?.let {
                    for (item in it)
                        mItems.add(DefaultItemList(item.id, item.name))
                }
                configureListView(mItems)
            }

            override fun failedApi(response: Response<List<GithubRepoResponseModel>>?, message: String) {
                CustomProgressDialog.closeDialog()
            }
        }, "mojombo")
    }

    private fun configureListView(mItems: List<DefaultItemList>) {
        list?.adapter = ExampleAdapterListView(mItems)
    }
}