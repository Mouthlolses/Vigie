package com.seuvigie.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seuvigie.presentation.utils.BillType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddReminder(
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (BillType, Boolean, Int?) -> Unit
) {
    if (!openDialog) return

    var selectedType by remember { mutableStateOf(BillType.ELECTRICITY) }
    var isRecurring by remember { mutableStateOf(false) }
    var months by remember { mutableIntStateOf(1) }

    BasicAlertDialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Nova Conta",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(26.dp))

                // 🧾 Tipo da conta
                Text("Tipo da conta")

                Spacer(modifier = Modifier.height(8.dp))

                BillType.entries.forEach { type ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedType = type }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedType == type,
                            onClick = { selectedType = type },
                            colors = RadioButtonDefaults.colors(
                                Color(0xFF5A00D1)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(type.label)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔁 Recorrente
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isRecurring,
                        onCheckedChange = { isRecurring = it },
                        colors = CheckboxDefaults.colors(
                            Color(0xFF5A00D1)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Conta recorrente")
                }

                // 📆 Se for recorrente, mostra seleção de meses
                if (isRecurring) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Duração (meses): $months")

                    Slider(
                        value = months.toFloat(),
                        onValueChange = { months = it.toInt() },
                        valueRange = 1f..12f,
                        steps = 10,
                        colors = SliderDefaults.colors(
                            Color(0xFF5A00D1),
                            Color(0xFF5A00D1),
                            Color(0xFF5A00D1),
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 🔘 Botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                    ) {
                        Text(
                            text = "Cancelar",
                            color = Color(0xFF5A00D1)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onSave(
                                selectedType,
                                isRecurring,
                                if (isRecurring) months else null
                            )
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5A00D1)
                        )
                    ) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}