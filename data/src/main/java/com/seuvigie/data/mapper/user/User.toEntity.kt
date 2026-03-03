package com.seuvigie.data.mapper.user

import com.seuvigie.data.model.UserEntity
import com.seuvigie.domain.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        lastName = lastName,
        phone = phone,
        email = email
    )
}