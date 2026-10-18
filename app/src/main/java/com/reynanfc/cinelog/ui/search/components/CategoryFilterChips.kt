package com.reynanfc.cinelog.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reynanfc.cinelog.ui.search.model.SearchCategory
import com.reynanfc.cinelog.ui.theme.CineLogChipSelectedContainer
import com.reynanfc.cinelog.ui.theme.CineLogChipSelectedLabel
import com.reynanfc.cinelog.ui.theme.CineLogChipUnselectedLabel
import com.reynanfc.cinelog.ui.theme.CineLogTheme

@Composable
fun CategoryFilterChips(
    selectedCategory: SearchCategory,
    onCategorySelected: (SearchCategory) -> Unit,
    modifier: Modifier = Modifier,
    categories: List<SearchCategory> = SearchCategory.entries,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory

            FilterChip(
                selected = isSelected,
                onClick = { onCategorySelected(category) },
                label = {
                    Text(
                        text = category.label,
                        color = if (isSelected) CineLogChipSelectedLabel else CineLogChipUnselectedLabel
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Transparent,
                    selectedContainerColor = CineLogChipSelectedContainer
                ),
                border = null
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF120A09)
@Composable
private fun CategoryFilterChipsPreview() {
    var selectedCategory by remember { mutableStateOf(SearchCategory.ALL) }
    CineLogTheme {
        CategoryFilterChips(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
    }
}
