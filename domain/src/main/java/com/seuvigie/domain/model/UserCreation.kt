package com.seuvigie.domain.model

//input de cadastro
data class UserCreation(
    val id: Long? = null,
    val remoteUiId: String? = null,
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)