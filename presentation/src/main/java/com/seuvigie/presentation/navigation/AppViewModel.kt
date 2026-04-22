package com.seuvigie.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.seuvigie.domain.usecase.user.CheckAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase
) : ViewModel() {


    var isLogged by mutableStateOf<Boolean?>(null)
        private set

    init {
        isLogged = runCatching {
            checkAuthUseCase()
        }.getOrElse {
            false
        }
    }
}