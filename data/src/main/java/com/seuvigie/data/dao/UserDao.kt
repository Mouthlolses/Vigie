package com.seuvigie.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seuvigie.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE name = :name")
    suspend fun findUsersByName(name: String) : List<UserEntity>

}