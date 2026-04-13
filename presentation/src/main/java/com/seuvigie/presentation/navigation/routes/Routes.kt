package com.seuvigie.presentation.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Splash : Routes()

    @Serializable
    data object Login : Routes()

    @Serializable
    data object Register : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object Details : Routes()


    @Serializable
    data object CreateBill : Routes() {

    }

}