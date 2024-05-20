package com.example.mytemplate

import android.app.Application
import com.example.mytemplate.config.GlobalConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        // configure timber
        if (GlobalConfig.IS_DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}