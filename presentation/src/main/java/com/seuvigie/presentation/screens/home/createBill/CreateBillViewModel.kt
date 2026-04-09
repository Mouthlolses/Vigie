package com.seuvigie.presentation.screens.home.createBill

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CreateBillViewModel @Inject constructor(

) : ViewModel() {


    private val _uiState = MutableStateFlow(CreateBillUiState())
    val uiState: StateFlow<CreateBillUiState> = _uiState.asStateFlow()


    private val _uiEvent = MutableSharedFlow<CreateBillEvent>()
    val uiEvent: SharedFlow<CreateBillEvent> = _uiEvent.asSharedFlow()




}