package com.reynanfc.cinelog.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineLogOptionsBottomSheet(
    onDismissRequest: () -> Unit,
    onThemeClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color(0xFF1E1E1E)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Opções",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            ListItem(
                headlineContent = { Text("Tema do aplicativo", color = Color.White) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = "Tema",
                        tint = Color(0xFFF75F4F)
                    )
                },
                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                modifier = Modifier.clickable {
                    onThemeClick()
                    onDismissRequest()
                }
            )

            ListItem(
                headlineContent = { Text("Sobre o CineLog", color = Color.White) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Sobre",
                        tint = Color(0xFFF75F4F)
                    )
                },
                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                modifier = Modifier.clickable {
                    onAboutClick()
                    onDismissRequest()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CineLogOptionsBottomSheetPreview() {
    CineLogOptionsBottomSheet(
        onDismissRequest = {},
        onThemeClick = {},
        onAboutClick = {});
}