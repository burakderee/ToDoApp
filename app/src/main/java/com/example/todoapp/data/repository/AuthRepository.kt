package com.example.todoapp.data.repository

import com.example.todoapp.data.local.UserDao
import com.example.todoapp.data.local.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun login(username: String, passwordHash: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user != null && user.passwordHash == passwordHash
    }

    suspend fun register(username: String, passwordHash: String): Boolean {
        val existingUser = userDao.getUserByUsername(username)
        if (existingUser != null) {
            return false // Kullanıcı zaten var
        }
        userDao.insertUser(UserEntity(username, passwordHash))
        return true
    }
}