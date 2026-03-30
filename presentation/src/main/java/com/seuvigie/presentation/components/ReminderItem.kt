package com.seuvigie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ReminderItem(
    month: String = "January",
    days: Int = 2,
    isPast: Boolean = true,
    onClick: () -> Unit = {},
    rowEnable: Boolean = false
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(26.dp)
            )
            .clickable(
                enabled = rowEnable,
                onClick = {
                    onClick()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = month,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            LazyRow(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(3) {
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPast) Color.Red else Color(0xFF7B1FFF)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text("Details", fontSize = 12.sp)
                    }
                }
            }
        }

        FlipCounter(days = days, isPast = isPast)
    }
}

@Composable
fun FlipCounter(days: Int, isPast: Boolean) {

    val bgColor = if (isPast) Color.Red else Color(0xFF7B1FFF)

    Box(
        modifier = Modifier
            .size(60.dp)
            .background(bgColor, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "%02d".format(days),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}