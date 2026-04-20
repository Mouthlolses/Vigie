package com.seuvigie.domain.usecase.user

import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Result<Unit> {
       return repository.logout()
    }
}