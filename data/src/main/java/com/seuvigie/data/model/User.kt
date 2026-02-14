package com.seuvigie.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = false)
    val id: Long?,

    var name: String,
    var email: String,
    var phone: String,
    var password: String
) : Parcelable
