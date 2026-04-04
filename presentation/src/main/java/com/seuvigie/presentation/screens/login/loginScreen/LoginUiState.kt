package com.seuvigie.presentation.screens.login.loginScreen

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)


sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
    data class ShowErrorMessage(val errorMessage: String?) : LoginEvent()
}