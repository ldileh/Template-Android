package com.example.mytemplate.data.remote

import com.example.core.base.BaseService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: RemoteService): BaseService() {

    suspend fun getUserRepo(username: String) = getResult {
        service.getUserRepos(username)
    }
}