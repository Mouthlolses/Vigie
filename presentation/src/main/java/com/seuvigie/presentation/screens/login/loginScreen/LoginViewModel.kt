package com.seuvigie.presentation.screens.login.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.usecase.LoginWithEmailUseCase
import com.seuvigie.domain.usecase.LoginWithGoogleUseCase
import com.seuvigie.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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
    val event: SharedFlow<LoginEvent> = _event.asSharedFlow()


    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun loginWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                if (email.isEmpty() || password.isEmpty()) {
                    _event.emit(
                        LoginEvent.ShowErrorMessage(
                            errorMessage = "Ops! Você esqueceu de preencher algum campo"
                        )
                    )
                    return@launch
                }

                _uiState.update { it.copy(isLoading = true) }

                val result = loginWithEmailUseCase(email, password)

                result.fold(
                    onSuccess = {
                        _uiState.update { it.copy(isLoading = false, isSuccess = true) }

                        _event.emit(LoginEvent.NavigateToHome)
                    },
                    onFailure = {
                        _uiState.update { it.copy(isLoading = false) }

                        _event.emit(
                            LoginEvent.ShowErrorMessage(
                                result.exceptionOrNull()?.message ?: "Erro desconhecido"
                            )
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false) }

                _event.emit(
                    LoginEvent.ShowErrorMessage("Erro: ${e.message}")
                )
            }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {

                _uiState.update { it.copy(isLoading = true) }

                val result = loginWithGoogleUseCase(idToken)

                result.fold(
                    onSuccess = {
                        val user = result.getOrNull()

                        user?.let {
                            saveUserUseCase(it)
                        }

                        _uiState.update {
                            it.copy(isLoading = false, isSuccess = true)
                        }

                        _event.emit(LoginEvent.NavigateToHome)
                    },
                    onFailure = {
                        _uiState.update { it.copy(isLoading = false, isSuccess = false) }
                        _event.emit(
                            LoginEvent.ShowErrorMessage(
                                result.exceptionOrNull()?.message ?: "Erro desconhecido"
                            )
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, isSuccess = false) }
                _event.emit(
                    LoginEvent.ShowErrorMessage("Erro: ${e.message}")
                )
            }
        }
    }
}