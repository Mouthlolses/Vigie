package com.seuvigie.data.remoteDataSource

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.seuvigie.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun loginWithGoogle(idToken: String): Result<User>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun logout(): Result<Unit>
}


class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) : AuthRemoteDataSource {

    override suspend fun loginWithGoogle(idToken: String): Result<User> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            val authResult = firebaseAuth
                .signInWithCredential(credential)
                .await()

            val firebaseUser = authResult.user

            val user = User(
                uid = firebaseUser?.uid ?: "",
                name = firebaseUser?.displayName ?: "",
                email = firebaseUser?.email ?: ""
            )

            Result.success(user)
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

    override suspend fun logout(): Result<Unit> {
        return try {

            firebaseAuth.signOut()
            googleSignInClient.signOut().await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

}