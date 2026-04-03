package com.seuvigie.domain.usecase

import com.seuvigie.domain.model.User
import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(idToken: String): Result<User> {
        return repository.loginWithGoogle(idToken)
    }



}