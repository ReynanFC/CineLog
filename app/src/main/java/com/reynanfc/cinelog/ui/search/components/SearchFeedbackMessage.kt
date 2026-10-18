package com.reynanfc.cinelog.ui.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reynanfc.cinelog.ui.theme.CineLogFeedbackIconContainer
import com.reynanfc.cinelog.ui.theme.CineLogTheme

@Composable
fun SearchEmptyMessage(modifier: Modifier = Modifier) {
    SearchFeedbackMessage(
        icon = Icons.Outlined.SearchOff,
        title = "Nada por aqui",
        description = "Tente buscar por outro título ou gênero.",
        modifier = modifier
    )
}

@Composable
fun SearchErrorMessage(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SearchFeedbackMessage(
        icon = Icons.Outlined.CloudOff,
        title = "Algo deu errado",
        description = message,
        modifier = modifier,
        actionLabel = "Tentar novamente",
        onActionClick = onRetryClick
    )
}

@Composable
private fun SearchFeedbackMessage(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(CineLogFeedbackIconContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(44.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        if (actionLabel != null) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onActionClick) {
                Text(text = actionLabel)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF120A09)
@Composable
private fun SearchEmptyMessagePreview() {
    CineLogTheme {
        SearchEmptyMessage()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF120A09)
@Composable
private fun SearchErrorMessagePreview() {
    CineLogTheme {
        SearchErrorMessage(
            message = "Não foi possível carregar os resultados. Verifique sua conexão e tente novamente.",
            onRetryClick = {}
        )
    }
}
