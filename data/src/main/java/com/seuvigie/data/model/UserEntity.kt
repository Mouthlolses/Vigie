package com.seuvigie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
)

