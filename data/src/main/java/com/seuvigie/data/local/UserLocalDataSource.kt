package com.seuvigie.data.local

import com.seuvigie.data.dao.UserDao
import com.seuvigie.data.model.UserEntity
import jakarta.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun insert(user: UserEntity) {
        userDao.insertUser(user)
    }
}