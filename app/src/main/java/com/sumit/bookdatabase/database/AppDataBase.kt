package com.sumit.bookdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sumit.bookdatabase.data.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}