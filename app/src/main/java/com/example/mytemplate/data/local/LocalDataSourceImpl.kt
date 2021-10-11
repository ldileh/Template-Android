package com.example.mytemplate.data.local

import android.content.Context
import com.example.mytemplate.config.AppDatabase
import com.example.mytemplate.config.SessionManager
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val context: Context
) : LocalDataSource{

    private val database by lazy { AppDatabase.getAppDatabase(context) }
    private val sessionManager by lazy { SessionManager(context) }
}