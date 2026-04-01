package com.seuvigie.domain.usecase

import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUserWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: RegisterRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.registerUser(email, password)
    }


}