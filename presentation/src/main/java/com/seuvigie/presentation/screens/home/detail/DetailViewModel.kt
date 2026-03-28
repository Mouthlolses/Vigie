package com.seuvigie.presentation.screens.home.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(

) : ViewModel() {


    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.IsLoading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()


}