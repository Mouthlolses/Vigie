package com.seuvigie.data.mapper

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.UserCreation

fun UserCreation.toEntity(uid: String): UserEntity {
    return UserEntity(
        remoteUiId = uid,
        name = name,
        email = email,
        phone = phone,
        syncPending = syncPending
    )
}
