package com.example.roomapp.data

import com.example.petcarekotlin.LoginData.User
import com.example.petcarekotlin.LoginData.UserDao

class UserRepository(private val userDao: UserDao) {


    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun getUser(email: String, password: String) = userDao.getUser(email, password)


}