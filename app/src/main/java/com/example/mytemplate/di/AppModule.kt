package com.example.mytemplate.di

import android.content.Context
import com.example.core.base.BaseService
import com.example.mytemplate.BuildConfig
import com.example.mytemplate.config.GlobalConfig
import com.example.mytemplate.domain.local.LocalDataSource
import com.example.mytemplate.domain.remote.RemoteDataSource
import com.example.mytemplate.domain.remote.RemoteService
import com.example.mytemplate.domain.usecase.MainUseCase
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
    fun provideService() = BaseService.createService(
        serviceClass = RemoteService::class.java,
        url = BuildConfig.SERVER_URL,
        isDebug = GlobalConfig.IS_DEBUG
    )

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: RemoteService) = RemoteDataSource(service)

    /**
     * @param context Context of application
     */
    @Singleton
    @Provides
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ) = LocalDataSource(context)

    /**
     * @param context Context of Application
     * @param remoteDataSource Remote data source (example : Endpoint)
     * @param localDataSource local data source (example : database local device or shared preference data)
     */
    @Singleton
    @Provides
    fun provideUseCase(
        @ApplicationContext context: Context,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ) = MainUseCase(context, remoteDataSource, localDataSource)
}