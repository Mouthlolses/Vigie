package com.seuvigie.presentation.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.model.UserCreation
import com.seuvigie.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.value = uiState.value.copy(userName = value)
    }

    fun updateEmail(value: String) {
        _uiState.value = uiState.value.copy(email = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = uiState.value.copy(password = value)
    }

    fun updatePhoneNumber(value: String) {
        _uiState.value = uiState.value.copy(phone = value)
    }

    fun updateConfirmPassword(value: String) {
        _uiState.value = uiState.value.copy(confirmPassword = value)
    }


    fun registerUser() {
        val userName = uiState.value.userName
        val email = uiState.value.email.trim()
        val password = uiState.value.password
        val phoneNumber = uiState.value.phone
        val confirmPassword = uiState.value.confirmPassword

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            _uiState.value = uiState.value.copy(
                loginError = true,
                loginErrorMessage = "Preencha todos os campos"
            )
            return
        }

        if (password.length < 6) {
            _uiState.value = uiState.value.copy(
                loginError = true,
                loginErrorMessage = "A senha deve ter no mínimo 6 caracteres"
            )
            return
        }

        if (password != confirmPassword) {
            _uiState.value =
                uiState.value.copy(loginError = true, loginErrorMessage = "As senhas não coincidem")
            return
        }

        viewModelScope.launch {
            _uiState.value = uiState.value.copy(load = true)

            val user = UserCreation(
                name = userName,
                email = email,
                phone = phoneNumber,
                password = password,
                syncPending = false
            )

            try {
                createUserUseCase(user)

                _uiState.value = uiState.value.copy(
                    goToInit = true,
                    loginError = false
                )
            } catch (e: Exception) {
                val errorMessage = if (e.message?.contains("email address is already") == true) {
                    "Email já cadastrado"
                } else {
                    "Erro ao criar conta, tente novamente"
                }
                _uiState.value = uiState.value.copy(
                    loginError = true,
                    loginErrorMessage = errorMessage
                )
            } finally {
                _uiState.value = uiState.value.copy(load = false)
            }
        }
    }

}