package com.seuvigie.data.remoteDataSource

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun loginWithGoogle(idToken: String): Result<User>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit>

    suspend fun getCurrentUser(): Result<User>

    suspend fun getUserData(): Result<List<Bill>>

    fun isUserLogged(): Boolean

    suspend fun logout(): Result<Unit>
}


class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
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

    override suspend fun getCurrentUser(): Result<User> {
        return try {

            val uid = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("Usuário não autenticado"))

            val snapshot = firestore
                .collection("users")
                .document(uid)
                .get()
                .await()

            if (!snapshot.exists()) {
                return Result.failure(Exception("Usuário não encontrado"))
            }

            val user = snapshot.toObject(User::class.java)
                ?: return Result.failure(Exception("Erro ao converter usuário"))

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserData(): Result<List<Bill>> {
        return try {

            val uid = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("Usuário não autenticado"))

            val snapshot = firestore
                .collection("users")
                .document(uid)
                .collection("bills")
                .get()
                .await()

            val bills = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Bill::class.java)
            }


            Result.success(bills)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun isUserLogged(): Boolean {
        return firebaseAuth.currentUser != null
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