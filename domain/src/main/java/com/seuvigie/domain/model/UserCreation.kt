package com.seuvigie.domain.model

//input de cadastro
data class UserCreation(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val syncPending: Boolean
)