package com.example.mytemplate.config

import com.example.mytemplate.BuildConfig

object GlobalConfig {
    // check if build app is debug or not
    const val IS_DEBUG = BuildConfig.BUILD_TYPE == "debug"

    // name of app
    private const val APP_NAME = "app"

    // shared preference
    const val SHARED_PREFERENCE_SESSION = "${APP_NAME}_sp_session"

    // name of database
    const val DB_NAME = "${APP_NAME}_db"
}