package com.seuvigie.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object Login : Routes()

    @Serializable
    data object Register : Routes()

    @Serializable
    data object OnBoarding : Routes()

    @Serializable
    data object Home : Routes()
}
