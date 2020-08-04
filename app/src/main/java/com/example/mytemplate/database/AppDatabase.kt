package com.example.mytemplate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytemplate.database.dao.AuthUserDao

@Database(entities = [(AuthUser::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userData(): AuthUserDao

    companion object{
        private const val DB_NAME = "user_db"

        fun getAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
    }
}