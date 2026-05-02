package com.seuvigie.domain.repository

import com.seuvigie.domain.model.Bill

interface BillRepository {

    suspend fun getBillById(id: String): Result<Bill>

}