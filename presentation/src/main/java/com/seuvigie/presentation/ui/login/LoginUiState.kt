package com.seuvigie.presentation.ui.login

data class LoginUiState(
    val userEmail: String = "",
    val password: String = "",
    val loginError: Boolean = false,
    val goToRegister: Boolean = false,
    val goToInit: Boolean = false
)
