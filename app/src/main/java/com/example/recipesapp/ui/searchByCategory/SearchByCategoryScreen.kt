package com.example.recipesapp.ui.searchByCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.recipesapp.ui.searchByCategory.Success

@Composable
fun SearchByCategoryScreen( onMealClick : (id : String) -> Unit){
    val viewModel : SearchByCategoriesViewModel = viewModel()

    when(val uiState = viewModel.uiState.collectAsState().value){
        is Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
        is Success -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = uiState.res.meals ?: emptyList()){
                    meal -> SearchResultCard(
                name = meal.strMeal ?: "Unknown",
                country = meal.strCountry ?: "Unknown",
                id = meal.idMeal ?: "Unknown",
                image = meal.strMealThumb ?: "https://unsplash.com/photos/a-close-up-of-a-grey-surface-kRO-eKzSonM",
                onCardClick = onMealClick
            )
            }
        }
        is Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.errorMessage,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}