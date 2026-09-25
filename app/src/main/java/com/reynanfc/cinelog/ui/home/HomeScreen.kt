package com.reynanfc.cinelog.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reynanfc.cinelog.ui.components.CineLogBottomBar
import com.reynanfc.cinelog.ui.components.CineLogOptionsBottomSheet
import com.reynanfc.cinelog.ui.components.CineLogTopBar

@Composable
fun HomeScreen() {
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF120A09),
        topBar = {
            CineLogTopBar(
                onMenuClick = { showBottomSheet = true }
            )
        },
        bottomBar = {
            CineLogBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "O que vamos assistir?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
    }

    if (showBottomSheet) {
        CineLogOptionsBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            onThemeClick = {},
            onAboutClick = {}
        )
    }
}

@Preview
@Composable
fun CineLogTopBarPreview() {
    HomeScreen()
}
