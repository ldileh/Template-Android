package com.example.mytemplate.di

import android.content.Context
import com.example.mytemplate.base.BaseService
import com.example.mytemplate.data.local.LocalDataSourceImpl
import com.example.mytemplate.data.remote.RemoteDataSource
import com.example.mytemplate.data.remote.RemoteService
import com.example.mytemplate.data.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideService() = BaseService.createService(RemoteService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: RemoteService) = RemoteDataSource(service)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ) = LocalDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSourceImpl,
    ) = MainUseCase(remoteDataSource, localDataSource)
}