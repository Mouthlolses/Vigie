package com.seuvigie.presentation.screens.home

sealed class HomeUiState {

    data object IsLoading : HomeUiState()

    data class Success(val data: List<String>) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}
