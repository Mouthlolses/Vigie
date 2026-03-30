package com.seuvigie.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddReminder(
    openDialog: Boolean = false, // state
    onDismiss: () -> Unit = {} // event
) {

        BasicAlertDialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Dialog")

                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("Fechar")
                    }
                }
            }
        }
}