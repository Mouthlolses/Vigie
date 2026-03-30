package com.seuvigie.presentation.screens.home.detail.screensForDetails.bill

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seuvigie.presentation.components.CardBills

@Composable
fun BillScreen() {

    LazyColumn {
        items(3) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 2.dp)
                    .padding(top = 12.dp)
            ) {
                CardBills()
            }
        }
    }


}