package com.seuvigie.presentation.screens.login.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.usecase.user.RegisterUserWithEmailAndPasswordUseCase
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
class RegisterViewModel @Inject constructor(
    private val registerUserWithEmailAndPassword: RegisterUserWithEmailAndPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()


    private val _event = MutableSharedFlow<RegisterEvent>()
    val event: SharedFlow<RegisterEvent> = _event.asSharedFlow()


    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }


    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            val result = registerUserWithEmailAndPassword(name = name, email, password)

            result.fold(
                onSuccess = {
                    _uiState.update {

                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }

                    _event.emit(
                        RegisterEvent.NavigateToHome
                    )
                },
                onFailure = {
                    _uiState.update {

                        it.copy(
                            isLoading = false,
                            isSuccess = false,
                        )
                    }

                    _event.emit(
                        RegisterEvent.ShowErrorMessage(result.exceptionOrNull()?.message)
                    )
                }
            )
        }
    }
}