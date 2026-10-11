package com.example.recipesapp.ui.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipesapp.ui.search.SearchResultCard


@Composable
fun SavedRecipes(modifier: Modifier = Modifier) {
    val viewModel: SavedRecipesViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is SavedRecipesUIState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
        is SavedRecipesUIState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.message,
                style = MaterialTheme.typography.titleLarge
            )
        }
        is SavedRecipesUIState.Success -> LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(horizontal = 10.dp)
        ) {
            items(items = uiState.res) { item ->
                Text(text = item.name ?: "Unknown", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}