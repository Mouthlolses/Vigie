package com.seuvigie.data.di

import android.content.Context
import androidx.room.Room
import com.seuvigie.data.dao.UserDao
import com.seuvigie.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vigie_database"
        ).build()


    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao = db.userDao()
}