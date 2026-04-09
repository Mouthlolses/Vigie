package com.seuvigie.presentation.screens.home.createBill

data class CreateBillUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)


sealed class CreateBillEvent {

    data object NavigateToHome : CreateBillEvent()

    data class ShowErrorMessage(val errorMessage: String?) : CreateBillEvent()
}
