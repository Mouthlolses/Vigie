package com.seuvigie.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.usecase.user.GetUserWithBillsUseCase
import com.seuvigie.domain.usecase.user.UserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserWithBillsUseCase: GetUserWithBillsUseCase,
    private val userLogoutUseCase: UserLogoutUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.IsLoading)

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<LogoutEvent>()
    val event: SharedFlow<LogoutEvent> = _event.asSharedFlow()


    init {
        getUserWithBills()
    }


    fun getUserWithBills() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.IsLoading
            try {
                val result = getUserWithBillsUseCase()

                result.fold(
                    onSuccess = { data ->
                        _uiState.value = HomeUiState.Success(
                            data = data
                        )
                    },
                    onFailure = {
                        _uiState.value = HomeUiState.Error(
                            it.message ?: "Erro inesperado"
                        )
                    }
                )

            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Erro inesperado")
            }
        }
    }


    fun logoutUser() {
        viewModelScope.launch {

            _uiState.value = HomeUiState.IsLoading

            val result = userLogoutUseCase()

            if (result.isSuccess) {

                _event.emit(LogoutEvent.NavigateToLogin)

            } else {
                _uiState.value = HomeUiState.Error("Erro ao sair")
            }
        }
    }
}
