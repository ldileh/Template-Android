package com.example.mytemplate.data.usecase

import android.content.Context
import android.os.Bundle
import com.example.core.base.BaseUseCase
import com.example.mytemplate.data.local.LocalDataSourceImpl
import com.example.mytemplate.data.remote.RemoteDataSource
import javax.inject.Inject

@Suppress("unused")
class MainUseCase @Inject constructor(
    context: Context,
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSourceImpl
): BaseUseCase(context) {

    suspend fun callUsers(username: String) = handleResponse { remoteData.getUserRepo(username) }

    suspend fun getTokenSession() = localData.getTokenSession()

    suspend fun setSession(data: Bundle) = localData.setSession(data)

    suspend fun clearSession() = localData.clearSession{ }
}