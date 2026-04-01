package com.seuvigie.data.remoteDataSource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface RegisterRemoteDataSource {

    suspend fun registerUser(email: String, password: String): Result<Unit>
}


class RegisterRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RegisterRemoteDataSource {

    override suspend fun registerUser(email: String, password: String): Result<Unit> {
        return try {

            firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}