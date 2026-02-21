package com.seuvigie.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.seuvigie.data.model.UserEntity
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class UserRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getUserProfile(uid: String): UserEntity {
        val document = firestore
            .collection("users")
            .document(uid)
            .get()
            .await()

        if (!document.exists()) {
            throw Exception("Usuário não encontrado no Firestore")
        }

        return document.toObject(UserEntity::class.java)
            ?: throw Exception("Erro ao mapear usuário")
    }

    suspend fun saveUser(user: UserEntity) {

        Log.d("UserRemote", "Entrou no saveUser")

        firestore
            .collection("users")
            .document(user.remoteUiId)
            .set(user)
            .await()

        Log.d("UserRemote", "Salvou no Firestore com sucesso")

    }
}