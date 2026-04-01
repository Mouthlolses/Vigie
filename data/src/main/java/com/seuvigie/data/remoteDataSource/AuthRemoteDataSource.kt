package com.seuvigie.data.remoteDataSource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun loginWithGoogle(idToken: String): Result<Unit>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    fun logout()

    fun getCurrentUser(): String?
}


class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteDataSource {

    override suspend fun loginWithGoogle(idToken: String): Result<Unit> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            firebaseAuth
                .signInWithCredential(credential)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): String? {
        return firebaseAuth.currentUser?.uid
    }

}