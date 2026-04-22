package com.seuvigie.presentation.screens.home

import com.seuvigie.domain.model.HomeData

sealed class HomeUiState {

    data object IsLoading : HomeUiState()

    data class Success(val data: HomeData) : HomeUiState()

    data class Error(val message: String) : HomeUiState()
}


sealed class LogoutEvent {

    data object NavigateToLogin : LogoutEvent()

}
