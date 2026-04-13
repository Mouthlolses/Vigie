package com.seuvigie.presentation.screens.login.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.LoginButton
import com.seuvigie.presentation.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBackToLogin: () -> Unit,
    onNavigateHome: () -> Unit
) {

    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val snackBarState = remember { SnackbarHostState() }

    var passwordVisible by remember { mutableStateOf(false) }


    val isValid = uiState.name.isNotBlank() &&
            uiState.email.contains("@") &&
            uiState.password.length >= 6

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {

                is RegisterEvent.NavigateToHome -> {
                    onNavigateHome()
                }

                is RegisterEvent.ShowErrorMessage -> {
                    snackBarState.showSnackbar(event.errorMessage ?: "Erro desconhecido")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackToLogin()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_action_outline_arrow_back),
                            contentDescription = "back",
                            modifier = Modifier
                                .size(26.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7B1FFF),
                            Color(0xFF5A00D1)
                        )
                    )
                )
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(120.dp))

            Text(
                text = "Criar Conta",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 6f
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nome
            CustomTextField(
                value = uiState.name,
                onValueChange = { viewModel.updateName(it) },
                placeholder = "Nome",
                icon = Icons.Default.Person
            )

            // Email
            CustomTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = "Email"
            )

            // Senha
            CustomTextField(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = "Password",
                icon = Icons.Default.Lock,
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(R.drawable.eye)
                    else
                        painterResource(R.drawable.eye_slash)

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            painter = image,
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier
                                .size(26.dp),
                            tint = Color.White
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

            CustomTextField(
                value = uiState.confirmPassword,
                onValueChange = { viewModel.updateConfirmPassword(it) },
                placeholder = "Confirm Password",
                icon = Icons.Default.Lock,
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(R.drawable.eye)
                    else
                        painterResource(R.drawable.eye_slash)

                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            painter = image,
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier
                                .size(26.dp),
                            tint = Color.White
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

            Spacer(modifier = Modifier.height(28.dp))

            LoginButton(
                text = "Cadastrar",
                enabled = isValid,
                onClick = {
                    viewModel.registerUser(
                        name = uiState.name,
                        email = uiState.email,
                        password = uiState.password
                    )
                },
                loading = uiState.isLoading
            )

            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}