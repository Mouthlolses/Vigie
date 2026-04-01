package com.seuvigie.data.repository

import com.seuvigie.data.remoteDataSource.RegisterRemoteDataSource
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource
) : RegisterRepository{

    override suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit> {
       return registerRemoteDataSource.registerUser(email,password)
    }

}