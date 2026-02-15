package com.seuvigie.domain.usecase

import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.createUser(user)
    }

}