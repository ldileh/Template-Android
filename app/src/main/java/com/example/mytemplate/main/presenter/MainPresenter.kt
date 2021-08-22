package com.example.mytemplate.main.presenter

import android.content.Context
import com.example.mytemplate.data.remote.GlobalApi
import com.example.mytemplate.base.BasePresenter
import com.example.mytemplate.base.ResponseApi
import com.example.mytemplate.main.model.pojo.DefaultItemList
import com.example.mytemplate.utils.UserMessage
import com.example.mytemplate.utils.checkResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val context: Context) : BasePresenter() {

    private val service: GlobalApi by lazy {
        GlobalApi(context)
    }

    fun callUsers(username: String, callback: ResponseApi<List<DefaultItemList>>) = launch {
        withContext(coroutineContext){
            service.getUserRepo(username)
                    .checkResponse(context) { message ->
                        UserMessage(context).defaultMessage("Response is empty")

                        callback.failedApi(message)
                    }
                    .let { result ->
                        callback.successApi(result?.map { DefaultItemList(it.id, it.name) } ?: mutableListOf())
                    }
        }
    }
}