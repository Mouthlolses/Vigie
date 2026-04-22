package com.seuvigie.domain.usecase.user

import com.seuvigie.domain.model.HomeData
import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserWithBillsUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Result<HomeData> {
        return try {
            val userResult = authRepository.getCurrentUser()
            val billsResult = authRepository.getUserData()

            if (userResult.isFailure) {
                return Result.failure(userResult.exceptionOrNull()!!)
            }

            if (billsResult.isFailure) {
                return Result.failure(billsResult.exceptionOrNull()!!)
            }

            Result.success(
                HomeData(
                    user = userResult.getOrThrow(),
                    bills = billsResult.getOrThrow()
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}