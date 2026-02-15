package com.seuvigie.data.mapper

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        password = password
    )
}