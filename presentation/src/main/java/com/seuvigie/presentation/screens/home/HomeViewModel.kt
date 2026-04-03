package com.seuvigie.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.usecase.GetCurrentUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.IsLoading)

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    init {
        getCurrentUser()
    }

    fun getCurrentUser() {
        viewModelScope.launch {

            _uiState.value = HomeUiState.IsLoading

            try {
                val result = getCurrentUserDataUseCase()

                result.fold(
                    onSuccess = { data ->
                        _uiState.value = HomeUiState.Success(data)
                    },
                    onFailure = { error ->
                        _uiState.value = HomeUiState.Error(error.message ?: "Erro inesperado")
                    }
                )

            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Erro inesperado")
            }
        }
    }
}
