package com.example.mytemplate.data.remote

import com.example.mytemplate.data.remote.model.GithubRepoResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutesApi {
    @GET("/users/{user}/repos")
    fun example(@Path("user") user: String): Call<List<GithubRepoResponseModel>>

    @GET("/index")
    fun index(): Call<Any>
}