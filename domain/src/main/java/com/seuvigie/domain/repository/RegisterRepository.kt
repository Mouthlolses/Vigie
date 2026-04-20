package com.seuvigie.domain.repository

import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.User

interface RegisterRepository {

    suspend fun registerUser(name: String, email: String, password: String): Result<Unit>

    suspend fun getCurrentUser(): Result<User>

    suspend fun registerBill(bill: Bill): Result<Bill>

    suspend fun saveUserIfNotExists(user: User)

}