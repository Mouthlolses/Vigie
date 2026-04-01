package com.seuvigie.domain.repository

interface AuthRepository {

    suspend fun loginWithGoogle(idToken: String): Result<Unit>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    fun logout()

    fun getCurrentUser(): String?
}