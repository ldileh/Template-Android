package com.example.mytemplate.domain.usecase

import android.os.Bundle
import com.example.core.base.BaseUseCase
import com.example.mytemplate.domain.local.LocalDataSource
import com.example.mytemplate.domain.remote.RemoteDataSource
import javax.inject.Inject

@Suppress("unused")
class MainUseCase @Inject constructor(
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSource
): BaseUseCase() {

    override fun onTokenExpired() {
        clearSession()
    }

    suspend fun callUsers(username: String) = handleResponse {
        remoteData.getUserRepo(username)
    }

    fun getTokenSession() = localData.getTokenSession()

    fun setSession(data: Bundle) = localData.setSession(data)

    @Suppress("WeakerAccess")
    fun clearSession() = localData.clearSession{
        // put action after clear session
    }
}