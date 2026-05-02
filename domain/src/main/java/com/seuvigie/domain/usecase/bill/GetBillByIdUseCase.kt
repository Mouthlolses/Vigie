package com.seuvigie.domain.usecase.bill

import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.repository.BillRepository
import javax.inject.Inject

class GetBillByIdUseCase @Inject constructor(
    private val repository: BillRepository
) {
    suspend operator fun invoke(id: String): Result<Bill> {
        return repository.getBillById(id)
    }
}