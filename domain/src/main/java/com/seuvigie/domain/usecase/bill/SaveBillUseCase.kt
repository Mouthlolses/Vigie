package com.seuvigie.domain.usecase.bill

import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class SaveBillUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(bill: Bill): Result<Unit> {
        return try {
            registerRepository.registerBill(bill)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}