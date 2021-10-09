package com.example.mytemplate.config

import com.example.mytemplate.BuildConfig

object GlobalConfig {
    // check if build app is debug or not
    const val IS_DEBUG = BuildConfig.BUILD_TYPE == "debug"

    // shared preference
    const val SHARED_PREFERENCE_SESSION = "template_sp_session"

    // name of database
    const val DB_NAME = "app_db"
}