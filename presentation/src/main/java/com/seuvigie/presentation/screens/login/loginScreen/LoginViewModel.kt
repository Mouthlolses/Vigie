package com.seuvigie.presentation.screens.login.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.usecase.LoginWithEmailUseCase
import com.seuvigie.domain.usecase.LoginWithGoogleUseCase
import com.seuvigie.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val loginWithGoogleUseCase: LoginWithGoogleUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    //State
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    //Event
    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()


    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun loginWithEmailAndPassword(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _uiState.update {
                it.copy(
                    errorMessage = "Ops! Você esqueceu de preencher algum campo"
                )
            }
            return
        }

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = loginWithEmailUseCase(email, password)

            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }

                _event.emit(LoginEvent.NavigateToHome)

            } else {
                _uiState.update { it.copy(isLoading = false) }
            }

        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = loginWithGoogleUseCase(idToken)

            if (result.isSuccess) {

                val user = result.getOrNull()

                user?.let {
                    saveUserUseCase(it)
                }

                _uiState.update {
                    it.copy(isLoading = false, isSuccess = true)
                }

                _event.emit(LoginEvent.NavigateToHome)
            } else {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = "Erro ao logar com Google"
                    )
                }
            }
        }
    }
}