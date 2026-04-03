package com.seuvigie.presentation.screens.login.loginScreen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.LoginButton
import com.seuvigie.presentation.components.LoginTextField


@Composable
fun LoginScreen(
    webClientId: String,
    onNavigate: () -> Unit,
    onNavigateHome: () -> Unit
) {

    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    var passwordVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                LoginEvent.NavigateToHome -> onNavigateHome()
            }
        }
    }

    val googleSignInClient = remember {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

        GoogleSignIn.getClient(context, options)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken

                if (idToken != null) {
                    viewModel.loginWithGoogle(idToken)
                }

            } catch (e: Exception) {
                // tratar erro
            }
        }
    }


    if (uiState.isLoading) {
        CircularProgressIndicator()
    }

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

        Spacer(modifier = Modifier.height(56.dp))

        Image(
            painter = painterResource(R.drawable.vigie_logo_melhorada_semfundo),
            contentDescription = "logo",
            modifier = Modifier
                .size(180.dp)
        )

        LoginTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = "Email"
        )

        LoginTextField(
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


        uiState.errorMessage?.let {
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(2.dp))

        LoginButton(
            onClick = {
                viewModel.loginWithEmailAndPassword(
                    email = uiState.email,
                    password = uiState.password
                )
            },
            loading = uiState.isLoading
        )


        Text(
            text = "Ou",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )


        Button(
            onClick = {
                launcher.launch(googleSignInClient.signInIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .padding(horizontal = 46.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.google_logo),
                    contentDescription = "google_img",
                    modifier = Modifier
                        .size(24.dp)
                )

                Text(
                    text = "Continuar com o Google",
                    color = Color(0xFF7B1FFF),
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                )
            }

        }

        Spacer(modifier = Modifier.height(2.dp))

        Row {
            Text(
                text = "Ainda não tem conta? ",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            Text(
                text = "Crie aqui",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    onNavigate()
                }
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Versão 1.0.0 (Build 1)",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(0.4f)
            )
        }

        Spacer(modifier = Modifier.height(160.dp))
    }
}
