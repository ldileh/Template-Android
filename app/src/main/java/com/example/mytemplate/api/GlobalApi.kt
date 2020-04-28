package com.example.mytemplate.api

import android.content.Context
import com.example.mytemplate.base.ResponseApi
import com.example.mytemplate.base.BaseActionApi
import com.example.mytemplate.config.RetrofitConfig
import com.example.mytemplate.main.model.api.GithubRepoResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalApi(context: Context) : BaseActionApi(context) {

    private fun clientService(): RoutesApi = RetrofitConfig
            .getRetrofitBuilder(RetrofitConfig.baseUrl)
            .create(RoutesApi::class.java)

    fun getUserRepo(callback: ResponseApi<List<GithubRepoResponseModel>>, username: String) {
        clientService()
                .example(username)
                .enqueue(object : Callback<List<GithubRepoResponseModel>> {

                    override fun onResponse(call: Call<List<GithubRepoResponseModel>>, response: Response<List<GithubRepoResponseModel>>) {
                        if (checkResponseIsSuccess(response))
                            callback.successApi(response)
                        else
                            callback.failedApi(response, response.message())
                    }

                    override fun onFailure(call: Call<List<GithubRepoResponseModel>>, t: Throwable) {
                        callback.failedApi(null, t.message ?: "")
                    }
                })
    }
}