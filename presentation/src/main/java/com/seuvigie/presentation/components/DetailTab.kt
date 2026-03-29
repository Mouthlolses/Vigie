package com.seuvigie.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seuvigie.presentation.utils.Destinations

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTab() {

    val navController = rememberNavController()
    val startDestination = Destinations.BILL

    var selectedDestinations by rememberSaveable {
        mutableStateOf(startDestination)
    }

    Column {

        // 🔹 Tabs
        SecondaryTabRow(
            selectedTabIndex = selectedDestinations.ordinal,
            containerColor = Color.Transparent,
            contentColor = Color(0xFF5A00D1),
            divider = {
                HorizontalDivider(color = Color.Transparent)
            }
        ) {
            Destinations.entries.forEach { destination ->

                Tab(
                    selected = selectedDestinations == destination,
                    onClick = {
                        selectedDestinations = destination

                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    text = {
                        Text(
                            text = destination.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Destinations.BILL.route) {
                Text("Tela de Contas")
            }
            composable(Destinations.REMINDERS.route) {
                Text("Tela de Lembretes")
            }
            composable(Destinations.PAIDBILLS.route) {
                Text("Tela de Contas Pagas")
            }
        }
    }
}