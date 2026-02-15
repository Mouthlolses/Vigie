package com.seuvigie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seuvigie.data.dao.UserDao
import com.seuvigie.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}