package com.seuvigie.data.remote

import com.google.firebase.auth.FirebaseAuth
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {

    suspend fun login(email: String, password: String): String {
        val result = auth
            .signInWithEmailAndPassword(email, password)
            .await()

        return result.user?.uid
            ?: throw Exception("User UID is null")
    }

    suspend fun register(email: String, password: String): String {
        val result = auth
            .createUserWithEmailAndPassword(email, password)
            .await()

        return result.user?.uid
            ?: throw Exception("User UID is null")
    }
}