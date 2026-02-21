package com.seuvigie.domain.usecase

import com.seuvigie.domain.model.LoginUser
import com.seuvigie.domain.repository.UserRepository
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: LoginUser) {
        repository.authUser(user)
    }
}