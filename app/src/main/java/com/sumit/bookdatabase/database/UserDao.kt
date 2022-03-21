package com.sumit.bookdatabase.database

import androidx.room.*
import com.sumit.bookdatabase.data.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Delete
    suspend fun deleteUser(user: User)
}