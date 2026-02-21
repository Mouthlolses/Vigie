package com.seuvigie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//→ modelo do banco
@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val remoteUiId: String = "",
    var name: String = "",
    var email: String = "",
    var phone: String = ""
)
