package com.seuvigie.presentation.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.model.User
import com.seuvigie.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    fun createUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ) {
        val user = User(
            id = null,
            name = name,
            email = email,
            phone = phone,
            password = password
        )
        viewModelScope.launch {
            createUserUseCase(user)
        }
    }

}