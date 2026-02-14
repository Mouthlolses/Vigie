package com.seuvigie.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview(showBackground = true)
@Composable
fun LoginScreen(
    onNavigateRegister: () -> Unit = {}
) {
    val viewModel: LoginViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()


    LaunchedEffect(state.goToRegister) {
        if (state.goToRegister) {
            onNavigateRegister()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Tela Login")
        Spacer(modifier = Modifier.padding(vertical = 28.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onNavigateRegister()
                }
            ){
                Text("Clique Aqui")
            }
        }
    }
}