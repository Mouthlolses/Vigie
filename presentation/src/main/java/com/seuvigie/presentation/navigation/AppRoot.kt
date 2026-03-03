package com.seuvigie.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seuvigie.presentation.navigation.routes.Routes
import com.seuvigie.presentation.screens.login.LoginScreen
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AppRoot() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        startDestination = Routes.Login,
        navController = navController
    ) {
        composable<Routes.Login>{
            LoginScreen()
        }
    }
}

