package com.example.mytemplate.data.remote

import android.content.Context
import com.example.mytemplate.base.BaseApi
import com.example.mytemplate.data.remote.model.GithubRepoResponseModel
import retrofit2.Response

class GlobalApi(context: Context) : BaseApi<RoutesApi>(context, RoutesApi::class.java) {

    fun getUserRepo(username: String): Response<List<GithubRepoResponseModel>> = createService()
            .example(username)
            .execute()
}