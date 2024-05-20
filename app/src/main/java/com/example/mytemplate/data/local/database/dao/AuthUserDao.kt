package com.example.mytemplate.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.mytemplate.data.local.database.AuthUser

@Dao
interface AuthUserDao {

    @Insert
    fun insertUser(authUser: AuthUser)
}