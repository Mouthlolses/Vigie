package com.seuvigie.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.model.LoginUser
import com.seuvigie.domain.model.UserCreation
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

    fun authUser(
        email: String,
        password: String
    ) {
        val user = LoginUser(
            email = email,
            password = password,
        )
        viewModelScope.launch {
            authUserUseCase.invoke(user)
        }

    }


}