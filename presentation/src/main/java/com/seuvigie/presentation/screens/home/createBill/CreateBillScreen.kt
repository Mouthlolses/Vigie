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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.seuvigie.domain.model.AccountType
import com.seuvigie.domain.model.Bill
import com.seuvigie.domain.model.RecurrenceType
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.CustomButton
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBillScreen(
    navigateToHome: () -> Unit,
    onBackHomeScreen: () -> Unit
) {

    val viewModel: CreateBillViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val snackBarHostState = remember { SnackbarHostState() }

    val recurrents = RecurrenceType.entries
    val recurrencyType = remember { mutableStateOf(uiState.recurrenceType?.name) }


    val accountTypes = AccountType.entries
    val accountSelection = remember { mutableStateListOf(uiState.accountTypes) }

    val dataVencimento = remember { mutableStateOf(uiState.expirationDate) }


    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CreateBillEvent.NavigateToHome -> {
                    navigateToHome()
                }

                is CreateBillEvent.ShowErrorMessage -> {
                    snackBarHostState.showSnackbar(event.errorMessage ?: "Erro desconhecido")
                }
            }
        }
    }


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
                            onBackHomeScreen()
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

                    Text(text = "Titulo")

                    OutlinedTextField(
                        value = uiState.title,
                        onValueChange = { viewModel.updateTitle(it) },
                        label = { Text("Descrição (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Tipo de Conta", color = Color.White)

                    accountTypes.forEach { tip ->
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Checkbox(
                                checked = accountSelection.contains(tip),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        accountSelection.add(tip)
                                    } else {
                                        accountSelection.remove(tip)
                                    }
                                }
                            )

                            Text(text = tip.name, color = Color.White)
                        }
                    }


                    Text("Recorrência", color = Color.White)

                    recurrents.forEach { rec ->
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            RadioButton(
                                selected = recurrencyType.value == rec.name,
                                onClick = {
                                    recurrencyType.value = rec.name
                                }
                            )

                            Text(rec.name, color = Color.White)
                        }
                    }


                    OutlinedTextField(
                        value = uiState.value,
                        onValueChange = { viewModel.updateValue(it) },
                        label = { Text("Valor (R$)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )


                    OutlinedTextField(
                        value = uiState.expirationDate,
                        onValueChange = { viewModel.updateExpirationDate(it) },
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

                    OutlinedTextField(
                        value = uiState.description,
                        onValueChange = { viewModel.updateDescription(it) },
                        label = { Text("Descrição (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    CustomButton(
                        text = "Salvar",
                        onClick = {
                            viewModel.createBill(
                                bill = Bill(
                                    title = uiState.title,
                                    description = uiState.description,
                                    expirationDate = uiState.expirationDate,
                                    accountTypes = uiState.accountTypes,
                                    recurrenceType = uiState.recurrenceType
                                )
                            )
                        },
                        loading = false
                    )
                }
            }
        }
    }
}

