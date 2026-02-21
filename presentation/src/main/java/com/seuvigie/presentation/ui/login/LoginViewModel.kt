package com.seuvigie.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.model.LoginUser
import com.seuvigie.domain.usecase.AuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    var uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateUserEmail(value: String) {
        _uiState.value = uiState.value.copy(userEmail = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = uiState.value.copy(password = value)
    }

    fun login() {
        val userEmail = uiState.value.userEmail
        val userPassword = uiState.value.password

        val user = LoginUser(
            email = userEmail,
            password = userPassword
        )
        viewModelScope.launch {
            try {
                authUserUseCase(user)
                _uiState.value = uiState.value.copy(
                    goToInit = true,
                    loginError = false,
                )
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    loginError = true
                )
            }
        }
    }
}