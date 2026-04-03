package com.seuvigie.domain.usecase

import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Result<Unit> {
       return repository.logout()
    }
}