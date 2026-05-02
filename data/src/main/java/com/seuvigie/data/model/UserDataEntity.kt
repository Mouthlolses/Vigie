package com.seuvigie.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.seuvigie.domain.model.AccountType

@Entity(
    tableName = "bills",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["uid"],
            childColumns = ["userOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userOwnerId")]
)
data class BillEntity(
    @PrimaryKey val billId: String,
    val userOwnerId: String, // 🔥 liga com o usuário
    val title: String,
    val recurrenceType: String,
    val expirationDate: String,
    val description: String,
    val accountTypes: AccountType,
    val createdAt: Long
)