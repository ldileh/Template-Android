package com.example.mytemplate.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.example.mytemplate.config.GlobalConfig

class SessionManager(private val context: Context) {

    companion object{
        const val KEY_TOKEN = "key_token"
        const val KEY_NAME = "key_name"
        const val KEY_USERNAME = "key_username"
    }

    @SuppressLint("CommitPrefEdits")
    fun setSession(data: Bundle?) {
        data?.let {
            // set editor
            val editor = getSessionSharedPreference().edit()
            editor.putString(KEY_TOKEN, data.getString(KEY_TOKEN))
            editor.putString(KEY_NAME, data.getString(KEY_NAME))
            editor.putString(KEY_USERNAME, data.getString(KEY_USERNAME))
            editor.apply()
            editor.commit()
        }
    }

    /**
     * Clear shared preference
     */
    @SuppressLint("CommitPrefEdits")
    fun clearSession(): Unit = getSessionSharedPreference().edit().clear().apply()

    fun getToken(): String? = getSessionSharedPreference().getString(KEY_TOKEN, null)

    fun getName(): String? = getSessionSharedPreference().getString(KEY_NAME, null)

    fun getUsername(): String? = getSessionSharedPreference().getString(KEY_USERNAME, null)

    fun isExist(): Boolean {
        val token = getToken()
        return token != null && token.isNotEmpty()
    }

    private fun getSessionSharedPreference() = context.getSharedPreferences(GlobalConfig.sharePreferenceSession, Context.MODE_PRIVATE)
}