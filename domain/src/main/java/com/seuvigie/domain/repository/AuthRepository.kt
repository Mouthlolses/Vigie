package com.seuvigie.domain.repository

import com.seuvigie.domain.model.User

interface AuthRepository {

    suspend fun loginWithGoogle(idToken: String): Result<User>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun getCurrentUser(): Result<User>

    fun isUserLogged(): Boolean

    suspend fun logout(): Result<Unit>
}