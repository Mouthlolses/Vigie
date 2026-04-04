package com.seuvigie.presentation.screens.login.registerScreen

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)


sealed class RegisterEvent {

    data object NavigateToHome : RegisterEvent()
    data class ShowErrorMessage(val errorMessage: String?) : RegisterEvent()

}