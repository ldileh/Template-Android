package com.example.mytemplate.data.local

import android.content.Context
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val context: Context
) : LocalDataSource