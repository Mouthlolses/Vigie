package com.seuvigie.presentation.screens.home.createBill

import com.seuvigie.domain.model.AccountType
import com.seuvigie.domain.model.RecurrenceType

data class CreateBillUiState(
    val title: String = "",
    val description: String = "",
    val value: String = "",
    val expirationDate: String = "",
    val accountTypes: List<AccountType?> = emptyList(),
    val recurrenceType: RecurrenceType? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)


sealed class CreateBillEvent {

    data object NavigateToHome : CreateBillEvent()

    data class ShowErrorMessage(val errorMessage: String?) : CreateBillEvent()
}
