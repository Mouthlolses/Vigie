package com.seuvigie.data.mapper

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.User

fun UserEntity.toDomain(): User {

    require(name.isNotBlank()) { "Nome inválido" }
    require(email.isNotBlank()) { "Email inválido" }

    return User(
        id = id,
        remoteUid = remoteUiId,
        name = name,
        email = email,
        phone = phone
    )
}