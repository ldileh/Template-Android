package com.example.mytemplate.data.usecase

import com.example.mytemplate.data.local.LocalDataSourceImpl
import com.example.mytemplate.data.remote.RemoteDataSource
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSourceImpl
) {

    suspend fun callUsers(username: String) = remoteData.getUserRepo(username)
}