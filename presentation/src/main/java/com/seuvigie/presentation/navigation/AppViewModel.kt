package com.seuvigie.presentation.navigation

import androidx.lifecycle.ViewModel
import com.seuvigie.domain.usecase.user.CheckAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase
) : ViewModel() {

    fun isUserLogged(): Boolean {
        return checkAuthUseCase()
    }
}