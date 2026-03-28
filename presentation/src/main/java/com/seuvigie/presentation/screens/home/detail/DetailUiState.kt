package com.seuvigie.presentation.screens.home.detail

sealed class DetailUiState {

    data object IsLoading : DetailUiState()

    data class Success(val data: List<String>) : DetailUiState()

    data class Error(val message: String) : DetailUiState()
}
