package com.seuvigie.presentation.screens.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.seuvigie.domain.usecase.bill.GetBillByIdUseCase
import com.seuvigie.presentation.navigation.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBillByIdUseCase: GetBillByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = savedStateHandle.toRoute<Routes.Details>()

    private val billId = args.billId

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.IsLoading)
    val uiState = _uiState.asStateFlow()

    init {
        loadBill()
    }

    private fun loadBill() {
        viewModelScope.launch {
            _uiState.value = DetailUiState.IsLoading
            try {
                val bill = getBillByIdUseCase(billId)

                bill
                    .fold(
                        onSuccess = { bill ->
                            _uiState.value = DetailUiState.Success(
                                data = bill
                            )
                        },
                        onFailure = {
                            _uiState.value = DetailUiState.Error(
                                it.message ?: "Erro Inesperado"
                            )
                        }
                    )

            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "Erro inesperado")
            }
        }
    }
}