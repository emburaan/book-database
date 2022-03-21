package com.sumit.bookdatabase.repository

import com.sumit.bookdatabase.data.User
import com.sumit.bookdatabase.database.UserDao
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun getUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}