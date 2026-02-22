package com.seuvigie.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//→ modelo do banco
@Entity(
    tableName = "users",
    indices = [Index(value = ["remoteUiId"], unique = true)]
)
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val remoteUiId: String,
    val name: String,
    val email: String,
    val phone: String,
    val syncPending: Boolean = false
)
