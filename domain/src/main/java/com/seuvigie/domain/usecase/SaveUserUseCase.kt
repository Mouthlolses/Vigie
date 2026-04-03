package com.seuvigie.domain.usecase

import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUserIfNotExists(user)
    }
}