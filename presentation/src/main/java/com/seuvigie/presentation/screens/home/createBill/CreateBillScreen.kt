package com.seuvigie.presentation.screens.home.createBill

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.LoginButton
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CreateBillScreen() {

    val snackBarHostState = remember { SnackbarHostState() }

    val contasSelecionadas = remember { mutableStateListOf<String>() }
    val recorrenciaSelecionada = remember { mutableStateOf("Mês") }

    val valor = remember { mutableStateOf("") }
    val descricao = remember { mutableStateOf("") }
    val dataVencimento = remember { mutableStateOf("") }

    val tiposConta = listOf("Luz", "Água", "Internet")
    val recorrencias = listOf("Dia", "Mês", "Ano")

    val context = LocalContext.current


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Nova conta",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

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
                    containerColor = Color(0xFF4C02B5)
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) { paddingValue ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF4C02B5),
                            Color(0xFF5A00D1)
                        )
                    )
                )
                .padding(paddingValue)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(0.15f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // -------------------------
                    // TIPOS DE CONTA
                    // -------------------------
                    Text("Tipo de Conta", color = Color.White)

                    tiposConta.forEach { tipo ->
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Checkbox(
                                checked = contasSelecionadas.contains(tipo),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        contasSelecionadas.add(tipo)
                                    } else {
                                        contasSelecionadas.remove(tipo)
                                    }
                                }
                            )

                            Text(tipo, color = Color.White)
                        }
                    }

                    // -------------------------
                    // RECORRENCIA
                    // -------------------------
                    Text("Recorrência", color = Color.White)

                    recorrencias.forEach { rec ->
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            RadioButton(
                                selected = recorrenciaSelecionada.value == rec,
                                onClick = {
                                    recorrenciaSelecionada.value = rec
                                }
                            )

                            Text(rec, color = Color.White)
                        }
                    }

                    // -------------------------
                    // VALOR
                    // -------------------------
                    OutlinedTextField(
                        value = valor.value,
                        onValueChange = { valor.value = it },
                        label = { Text("Valor (R$)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    // -------------------------
                    // DATA DE VENCIMENTO
                    // -------------------------
                    OutlinedTextField(
                        value = dataVencimento.value,
                        onValueChange = {},
                        label = { Text("Data de vencimento") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                val calendar = Calendar.getInstance()

                                DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        dataVencimento.value =
                                            "$dayOfMonth/${month + 1}/$year"
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()

                            },
                        enabled = false
                    )

                    // -------------------------
                    // DESCRIÇÃO
                    // -------------------------
                    OutlinedTextField(
                        value = descricao.value,
                        onValueChange = { descricao.value = it },
                        label = { Text("Descrição (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // -------------------------
                    // BOTÃO
                    // -------------------------
                    LoginButton(
                        text = "Salvar",
                        onClick = {

                            when {
                                contasSelecionadas.isEmpty() -> {
                                    // erro
                                }

                                valor.value.isBlank() -> {
                                    // erro
                                }

                                dataVencimento.value.isBlank() -> {
                                    // erro
                                }

                                else -> {
                                    // sucesso → salvar no banco
                                }
                            }

                        },
                        loading = false
                    )
                }
            }
        }
    }
}

