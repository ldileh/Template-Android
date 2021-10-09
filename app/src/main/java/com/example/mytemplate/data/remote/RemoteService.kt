package com.example.mytemplate.data.remote

import com.example.mytemplate.data.remote.model.GithubRepoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {
    @GET("/users/{user}/repos")
    suspend fun getUserRepos(@Path("user") user: String): Response<GithubRepoResponse>

    @GET("/index")
    suspend fun index(): Call<Any>
}