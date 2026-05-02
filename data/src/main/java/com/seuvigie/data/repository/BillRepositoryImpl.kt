package com.seuvigie.data.repository

import com.seuvigie.data.remoteDataSource.AuthRemoteDataSource
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.repository.BillRepository
import javax.inject.Inject


class BillRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : BillRepository {
    override suspend fun getBillById(id: String): Result<Bill> {
        return remote.getBillById(id)
    }
}