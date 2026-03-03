package com.seuvigie.data.localDataSource

import com.seuvigie.data.database.dao.UserDao
import com.seuvigie.data.model.UserEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }
}