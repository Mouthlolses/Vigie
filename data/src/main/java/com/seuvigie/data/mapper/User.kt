package com.seuvigie.data.mapper

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.UserCreation

fun UserCreation.toEntity(): UserEntity {
    return UserEntity(
        id = id ?: 0,
        remoteUiId = remoteUiId ?: "",
        name = name,
        email = email,
        phone = phone,
    )
}
