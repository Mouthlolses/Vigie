package com.seuvigie.presentation.ui.register

data class RegisterUiState(
    val userName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val load: Boolean = false,
    val loginError: Boolean = false,
    val goToRegister: Boolean = false,
    val goToInit: Boolean = false,
    val loginErrorMessage: String = "",
    val loginErrorMessageDetail: String = "",
)
