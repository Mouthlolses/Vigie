package com.seuvigie.data.remoteDataSource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface RegisterRemoteDataSource {

    suspend fun registerUser(name: String, email: String, password: String): Result<Unit>

    suspend fun registerBill(bill: Bill): Result<Bill>

    suspend fun saveUserIfNotExists(user: User)

}


class RegisterRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : RegisterRemoteDataSource {

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String,
    ): Result<Unit> {
        return try {

            val result = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = result.user
                ?: return Result.failure(Exception("Erro ao criar usuário"))


            val userData = User(
                uid = user.uid,
                name = name,
                email = user.email ?: "",
                createdAt = System.currentTimeMillis()
            )

            firestore
                .collection("users")
                .document(user.uid)
                .set(userData)
                .await()


            Result.success(Unit)

        } catch (e: Exception) {
            firebaseAuth.currentUser?.delete()
            Result.failure(e)
        }
    }

    override suspend fun registerBill(bill: Bill): Result<Bill> {
        return try {

            val uid = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("Usuário não autenticado"))

            val docRef = firestore
                .collection("users")
                .document(uid)
                .collection("bills")
                .document()

            val billWithId = bill.copy(
                id = docRef.id
            )

            docRef.set(billWithId).await()

            Result.success(billWithId)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun saveUserIfNotExists(user: User) {

        val doc = firestore.collection("users")
            .document(user.uid)
            .get()
            .await()

        if (!doc.exists()) {
            firestore
                .collection("users")
                .document(user.uid)
                .set(user)
        }
    }
}