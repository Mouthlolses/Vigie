package com.seuvigie.domain.usecase

import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUserWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: RegisterRepository
) {

    suspend operator fun invoke(name: String, email: String, password: String): Result<Unit> {
        return repository.registerUser(name = name, email = email, password = password)
    }


}