package com.example.mytemplate.data.usecase

import android.content.Context
import com.example.mytemplate.base.BaseUseCase
import com.example.mytemplate.data.local.LocalDataSourceImpl
import com.example.mytemplate.data.remote.RemoteDataSource
import javax.inject.Inject

class MainUseCase @Inject constructor(
    context: Context,
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSourceImpl
): BaseUseCase(context) {

    suspend fun callUsers(username: String) = handleResponse { remoteData.getUserRepo(username) }
}