package com.seuvigie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seuvigie.data.database.dao.UserDao
import com.seuvigie.data.model.BillEntity
import com.seuvigie.data.model.UserEntity

@Database(entities = [UserEntity::class, BillEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}