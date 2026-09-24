package com.reynanfc.cinelog.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reynanfc.cinelog.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineLogTopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF120a09)
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_cine_log),
                    contentDescription = "Logo do CineLog",
                    modifier = Modifier.size(32.dp)
                        .background(
                            color = Color(0xFFF75F4F),
                            shape = RoundedCornerShape(5.dp)
                        ),
                    tint = Color.Black,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Cine",
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Log",
                    color = Color(0xFFF75F4F),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Abrir menu",
                    tint = Color.White
                )
            }
        }
    )
}

@Preview(name = "TopBar", fontScale = 1.15f)
@Composable
fun CineLogTopBarPreview() {
    CineLogTopBar(onMenuClick = {})
}

