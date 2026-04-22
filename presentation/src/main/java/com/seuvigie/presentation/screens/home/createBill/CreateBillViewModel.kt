package com.seuvigie.presentation.screens.home.createBill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuvigie.domain.model.AccountType
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.RecurrenceType
import com.seuvigie.domain.usecase.bill.SaveBillUseCase
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
class CreateBillViewModel @Inject constructor(
    private val saveBill: SaveBillUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(CreateBillUiState())
    val uiState: StateFlow<CreateBillUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<CreateBillEvent>()
    val uiEvent: SharedFlow<CreateBillEvent> = _uiEvent.asSharedFlow()


    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateExpirationDate(expirationDate: String) {
        _uiState.update { it.copy(expirationDate = expirationDate) }
    }

    fun toggleAccountType(type: AccountType) {
        _uiState.update { state ->
            val updated = state.accountTypes.toMutableList().apply {
                if (contains(type)) remove(type) else add(type)
            }
            state.copy(accountTypes = updated)
        }
    }

    fun updateRecurrence(recurrenceType: RecurrenceType) {
        _uiState.update { it.copy(recurrenceType = recurrenceType) }
    }

    fun updateValue(value: String) {
        _uiState.update { it.copy(value = value) }
    }

    fun createBill(bill: Bill) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            val result = saveBill(bill)

            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                    _uiEvent.emit(
                        CreateBillEvent.NavigateToHome
                    )
                },
                onFailure = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = false
                        )
                    }
                    _uiEvent.emit(
                        CreateBillEvent.ShowErrorMessage(
                            result.exceptionOrNull()?.message
                        )
                    )
                }
            )

        }
    }
}