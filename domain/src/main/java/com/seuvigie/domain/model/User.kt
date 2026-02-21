package com.seuvigie.domain.model

//→ modelo principal do domínio
data class User(
    val id: Long?,
    val remoteUid: String?,
    val name: String,
    val email: String,
    val phone: String
)