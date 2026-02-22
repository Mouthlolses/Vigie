package com.seuvigie.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//Header – Resumo Inteligente do Mês
@Preview()
@Composable
fun CardHeader(
    onClickAction: () -> Unit = {}
) {
    ElevatedCard(
        onClick = { onClickAction() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Text(text = "Total previsto do mês: R$ 847,90")
            Text(text = "Já pago: R$ 420,00")
            Text(text = "Falta pagar: R$ 427,90")

            //Barra de progresso visual

//            Extras inteligentes:
//
//            📈 “+12% comparado ao mês passado”
//
//            💡 “Conta de luz está acima da média”
        }
    }
}