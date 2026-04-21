package com.seuvigie.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seuvigie.presentation.navigation.routes.Routes
import com.seuvigie.presentation.screens.home.HomeScreen
import com.seuvigie.presentation.screens.home.createBill.CreateBillScreen
import com.seuvigie.presentation.screens.home.detail.DetailScreen
import com.seuvigie.presentation.screens.login.loginScreen.LoginScreen
import com.seuvigie.presentation.screens.login.registerScreen.RegisterScreen
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AppRoot(webClient: String) {

    val viewmodel: AppViewModel = hiltViewModel()

    val navController: NavHostController = rememberNavController()

    val startDestination = if (viewmodel.isUserLogged()) {
        Routes.Home
    } else {
        Routes.Login
    }

    NavHost(
        startDestination = startDestination,
        navController = navController,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(700)
            ) + fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        }

    ) {
        composable<Routes.Login> {
            LoginScreen(
                webClientId = webClient,
                onNavigate = {
                    navController.navigate(
                        Routes.Register
                    )
                },
                onNavigateHome = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Routes.Register> {
            RegisterScreen(
                onBackToLogin = {
                    navController.popBackStack()
                },
                onNavigateHome = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Login) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Routes.Home> {
            HomeScreen(
                onNavigate = {
                    navController.navigate(
                        Routes.Details
                    )
                },
                onNavigateToCreationBill = {
                    navController.navigate(
                        Routes.CreateBill
                    )
                },
                onLogout = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Home) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable<Routes.Details> {
            DetailScreen(
                onBackHomeScreen = {
                    navController.popBackStack()
                }
            )
        }

        composable<Routes.CreateBill> {
            CreateBillScreen(
                navigateToHome = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.CreateBill)
                    }
                },
                onBackHomeScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}


