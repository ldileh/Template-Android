package com.example.mytemplate.database.dao

import androidx.room.Insert
import com.example.mytemplate.database.AuthUser

interface AuthUserDao {

    @Insert
    fun insertUser(authUser: AuthUser)
}