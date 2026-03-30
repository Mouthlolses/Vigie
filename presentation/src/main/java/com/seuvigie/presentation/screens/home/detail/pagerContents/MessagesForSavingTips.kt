package com.seuvigie.presentation.screens.home.detail.pagerContents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ScreenSavingTips() {



    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Desligue aparelhos da tomada quando não estiver usando — o modo stand-by também consome energia.\n" +
                    "Troque lâmpadas por LED: duram mais e gastam menos.\n" +
                    "Banhos mais curtos fazem diferença na conta de luz.\n" +
                    "Evite abrir a geladeira toda hora.\n" +
                    "Use a máquina de lavar com carga cheia."
        )
    }
}