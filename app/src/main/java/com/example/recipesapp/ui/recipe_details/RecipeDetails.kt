package com.example.recipesapp.ui.recipe_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.recipesapp.model.getIngredientsList


@Composable
fun RecipeDetails(modifier: Modifier = Modifier){
    val viewModel : RecipeDetailsViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val scrollState = rememberScrollState()
    when(uiState){
        is DetailsUIState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
        is DetailsUIState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.message,
                style = MaterialTheme.typography.titleLarge
            )
        }
        is DetailsUIState.Success -> Column(modifier = modifier.padding(horizontal = 10.dp).verticalScroll(scrollState)) {
            val meal = uiState.res.meals?.firstOrNull()
            AsyncImage(model = meal?.strMealThumb,
                contentDescription = "mealImage",
                error = rememberVectorPainter(Icons.Default.Warning),
                fallback = rememberVectorPainter(Icons.Default.Warning),
                modifier = Modifier.fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop)
            Column() {
                Text(meal?.strMeal ?: "Unknown", style = MaterialTheme.typography.titleLarge)
                Text(meal?.strTags ?: "No tags")//tags
                Text("Ingredients: ", style = MaterialTheme.typography.titleMedium)

                meal?.getIngredientsList()?.forEach { item ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(text = item.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                        Text(text = item.measure, style = MaterialTheme.typography.bodyMedium)
                } }
                Text("Instructions:", style = MaterialTheme.typography.titleMedium)
                Text(meal?.strInstructions ?: "No instruction")
            }

        }
    }

}