package com.seuvigie.domain.usecase.user

import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.RegisterRepository
import javax.inject.Inject

class GetCurrentUserDataUseCase @Inject constructor(
    private val repository: RegisterRepository
) {

    suspend operator fun invoke(): Result<User> {
        return repository.getCurrentUser()
    }
}