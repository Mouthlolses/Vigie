package com.seuvigie.domain.repository

interface RegisterRepository {

    suspend fun registerUser(email: String, password: String): Result<Unit>

}