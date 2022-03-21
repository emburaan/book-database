package com.sumit.bookdatabase.di

import android.app.Application
import androidx.room.Room
import com.sumit.bookdatabase.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideAppDatabase(application: Application) = Room
        .databaseBuilder(application, AppDataBase::class.java, "book-db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCartDao(appDatabase: AppDataBase) = appDatabase.userDao()
}
