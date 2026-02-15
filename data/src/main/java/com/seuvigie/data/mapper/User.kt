package com.seuvigie.data.mapper

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id ?: 0,
        name = name,
        email = email,
        phone = phone,
        password = password
    )
}
