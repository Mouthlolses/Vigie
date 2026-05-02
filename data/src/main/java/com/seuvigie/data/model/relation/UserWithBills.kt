package com.seuvigie.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.seuvigie.data.model.BillEntity
import com.seuvigie.data.model.UserEntity

data class UserWithBills(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userOwnerId"
    )
    val bills: List<BillEntity>
)