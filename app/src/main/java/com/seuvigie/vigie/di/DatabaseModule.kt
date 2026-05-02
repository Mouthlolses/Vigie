package com.seuvigie.vigie.di

import android.content.Context
import androidx.room.Room
import com.seuvigie.data.database.AppDatabase
import com.seuvigie.data.database.dao.UserDao
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
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vigie.db"
        ).build()
    }

    @Provides
    fun provideUserDao(
        db: AppDatabase
    ): UserDao = db.userDao()

}