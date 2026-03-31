package com.seuvigie.presentation.screens.login.loginScreen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.LoginButton
import com.seuvigie.presentation.components.LoginTextField


@Preview(showBackground = true)
@Composable
fun LoginScreen(
    onNavigate: () -> Unit = {},
    onNavigateHome: () -> Unit = {}

) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


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

        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "Vigie",
            fontSize = 42.sp,
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

        Spacer(modifier = Modifier.padding(vertical = 38.dp))


        LoginTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email"
        )

        LoginTextField(
            value = password,
            onValueChange = { password = it },
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

        Spacer(modifier = Modifier.height(8.dp))

        LoginButton(
            onClick = {
                onNavigateHome()
            }
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Ou",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )


        Spacer(modifier = Modifier.height(2.dp))

        Button(
            onClick = {

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

        Spacer(modifier = Modifier.height(220.dp))
    }
}
