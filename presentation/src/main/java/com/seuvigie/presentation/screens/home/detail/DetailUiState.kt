package com.seuvigie.presentation.screens.home.detail

import com.seuvigie.domain.model.Bill

sealed class DetailUiState {

    data object IsLoading : DetailUiState()

    data class Success(val data: Bill) : DetailUiState()

    data class Error(val message: String) : DetailUiState()
}
