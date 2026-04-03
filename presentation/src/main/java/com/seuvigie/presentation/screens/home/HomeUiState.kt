package com.seuvigie.presentation.screens.home

import com.seuvigie.domain.model.User

sealed class HomeUiState {

    data object IsLoading : HomeUiState()

    data class Success(val data: User) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}
