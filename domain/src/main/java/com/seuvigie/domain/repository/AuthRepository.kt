package com.seuvigie.domain.repository

import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.User

interface AuthRepository {

    suspend fun loginWithGoogle(idToken: String): Result<User>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun getCurrentUser(): Result<User>

    suspend fun getUserData(): Result<List<Bill>>

    fun isUserLogged(): Boolean

    suspend fun logout(): Result<Unit>
}