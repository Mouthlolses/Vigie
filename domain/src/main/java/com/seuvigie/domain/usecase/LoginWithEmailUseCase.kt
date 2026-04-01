package com.seuvigie.domain.usecase

import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class LoginWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.loginWithEmailAndPassword(email, password)
    }
}