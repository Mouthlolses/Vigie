package com.seuvigie.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.seuvigie.data.model.BillEntity
import com.seuvigie.data.model.UserEntity
import com.seuvigie.data.model.relation.UserWithBills
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBills(bills: List<BillEntity>)

    @Query("SELECT * FROM users LIMIT 1")
    fun observeUser(): Flow<UserEntity?>

    @Query("DELETE FROM users")
    suspend fun clear()


    @Transaction
    suspend fun saveUserWithBills(
        user: UserEntity,
        bill: List<BillEntity>
    ) {
        insertUser(user)
        insertBills(bill)
    }


    @Transaction
    @Query("SELECT * FROM users WHERE uid = :userId")
    fun getUserWithBills(userId: String): Flow<UserWithBills>
}