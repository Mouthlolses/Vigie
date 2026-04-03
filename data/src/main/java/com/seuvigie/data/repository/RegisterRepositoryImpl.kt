package com.seuvigie.data.repository

import com.seuvigie.data.remoteDataSource.RegisterRemoteDataSource
import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository {

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {
        return registerRemoteDataSource.registerUser(
            name = name,
            email = email,
            password = password
        )
    }

    override suspend fun getCurrentUser(): Result<User> {
        return registerRemoteDataSource.getCurrentUser()
    }

    override suspend fun saveUserIfNotExists(user: User) {
        registerRemoteDataSource.saveUserIfNotExists(user)
    }

}