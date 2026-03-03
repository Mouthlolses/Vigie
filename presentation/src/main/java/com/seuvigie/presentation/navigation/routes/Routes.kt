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


//    @Serializable
//    data class CategoryDetail(
//        val categoryId: String,
//        val categoryColor: Long = 0XFF034d58
//    ) : Routes()
//
//    @Serializable
//    data class Game(val categoryId: String) : Routes()

}