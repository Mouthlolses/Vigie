package com.seuvigie.domain.repository

import com.seuvigie.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User)

    suspend fun findUsersByName(name: String): List<User>

}