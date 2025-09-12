package com.example.todoapp.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("USER_ID_KEY", userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("USER_ID_KEY", null)
    }

    fun clearUserId() {
        sharedPreferences.edit().remove("USER_ID_KEY").apply()
    }
}