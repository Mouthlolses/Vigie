package com.seuvigie.data.repository

import com.seuvigie.data.remoteDataSource.RegisterRemoteDataSource
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val remote: RegisterRemoteDataSource
) : RegisterRepository {

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {
        return remote.registerUser(
            name = name,
            email = email,
            password = password
        )
    }


    override suspend fun registerBill(bill: Bill): Result<Bill> {
        return remote.registerBill(bill)
    }

    override suspend fun saveUserIfNotExists(user: User) {
        remote.saveUserIfNotExists(user)
    }

}