package com.seuvigie.domain.usecase.user

import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserDataUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Result<User> {
        return authRepository.getCurrentUser()
    }
}