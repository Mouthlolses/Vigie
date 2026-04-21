package com.seuvigie.domain.usecase.user

import com.seuvigie.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean {
        return authRepository.isUserLogged()
    }
}