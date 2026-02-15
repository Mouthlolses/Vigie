package com.seuvigie.data.repository

import com.seuvigie.data.dao.UserDao
import com.seuvigie.data.mapper.toDomain
import com.seuvigie.data.mapper.toEntity
import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun createUser(user: User) {
        userDao.insertUser(
            user.toEntity()
        )
    }


    override suspend fun findUsersByName(name: String): List<User> {
        return userDao
            .findUsersByName(name)
            .map { it.toDomain() }
    }


}