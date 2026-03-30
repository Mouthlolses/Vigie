package com.seuvigie.presentation.screens.home.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seuvigie.presentation.R
import com.seuvigie.presentation.components.DetailTab
import com.seuvigie.presentation.components.ReminderItem
import com.seuvigie.presentation.screens.home.detail.pagerContents.ScreenSavingTips


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailScreen(
    onBackHomeScreen: () -> Unit = {}
) {

    val pages: List<@Composable () -> Unit> = listOf(
        {
            ReminderItem(
                rowEnable = false
            )
        },
        {
            ScreenSavingTips()
        },
        {

        }
    )

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
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
                    containerColor = Color(0xFF5A00D1)
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(0.32f)
                        .fillMaxWidth()
                        .background(Color(0xFF5A00D1))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Details",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(50.dp))

                Card(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .heightIn(min = 125.dp, max = 200.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 26.dp, bottom = 26.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) { page ->
                        pages[page]()
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                DetailTab()
            }
        }
    }
}