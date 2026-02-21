package com.seuvigie.domain.repository

import com.seuvigie.domain.model.LoginUser
import com.seuvigie.domain.model.User
import com.seuvigie.domain.model.UserCreation

interface UserRepository {

    suspend fun createUser(user: UserCreation) : Result<User>

    suspend fun authUser(user: LoginUser) : Result<User>


}