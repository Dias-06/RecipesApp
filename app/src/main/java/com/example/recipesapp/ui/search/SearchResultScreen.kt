package com.example.recipesapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun SearchResultScreen(modifier: Modifier = Modifier, onMealClick : (id : String) -> Unit){
    val viewModel : SearchViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val text = viewModel.textInput.collectAsState().value
    Column(modifier = modifier.padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TextField(
            value = text,
            onValueChange = {cur -> viewModel.inputTextChange(cur)},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {IconButton(onClick = {viewModel.searchRecipe(text.trim())}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search recipe")
            }},
            placeholder = { Text("Search...") },
            singleLine = true
        )
        when(uiState){
            is Initial -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Search name of meal",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            is Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(modifier = Modifier.size(100.dp))
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

            is InCorrectSearch -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.message,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

    }

}
@Composable
fun SearchResultCard(modifier: Modifier = Modifier, name : String, country : String , image : String, id: String, onCardClick: (id: String) -> Unit){
    Card(modifier =modifier, onClick = {onCardClick(id)}) {
        Column{
            AsyncImage(model = image, contentDescription = "mealImage", modifier = Modifier.fillMaxWidth().height(180.dp), contentScale = ContentScale.Crop)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(name, style = MaterialTheme.typography.titleLarge)
                Text(country, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}