package com.example.mytemplate.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytemplate.data.local.database.AuthUser
import com.example.mytemplate.data.local.database.dao.AuthUserDao

@Database(entities = [(AuthUser::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userData(): AuthUserDao

    companion object{
        private const val DB_NAME = "user_db"

        fun getAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
    }
}