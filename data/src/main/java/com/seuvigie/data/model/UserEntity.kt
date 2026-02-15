package com.seuvigie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var name: String,
    var email: String,
    var phone: String,
    var password: String
)
