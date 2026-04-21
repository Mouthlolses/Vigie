package com.seuvigie.data.repository

import com.seuvigie.data.remoteDataSource.AuthRemoteDataSource
import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun loginWithGoogle(idToken: String): Result<User> {
        return remote.loginWithGoogle(idToken)
    }

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Result<Unit> {
        return remote.loginWithEmailAndPassword(email, password)
    }

    override suspend fun getCurrentUser(): Result<User> {
        return remote.getCurrentUser()
    }

    override fun isUserLogged(): Boolean {
        return remote.isUserLogged()
    }

    override suspend fun logout(): Result<Unit> {
        return remote.logout()
    }

}