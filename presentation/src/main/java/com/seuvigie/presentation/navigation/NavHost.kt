package com.seuvigie.presentation.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seuvigie.presentation.ui.login.LoginScreen
import com.seuvigie.presentation.ui.register.RegisterScreen


@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
) {


    Scaffold(

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SharedTransitionLayout {
                androidx.navigation.compose.NavHost(
                    navController = navController,
                    startDestination = Routes.Login,
                    modifier = Modifier,
                ) {
                    composable<Routes.Login> {
                        LoginScreen(
                            onNavigateRegister = { navController.navigate(Routes.Register)}
                        )
                    }

                    composable<Routes.Register> {
                        RegisterScreen()
                    }
                }
            }
        }
    }
}