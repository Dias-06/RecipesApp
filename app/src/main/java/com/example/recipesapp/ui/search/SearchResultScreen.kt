package com.example.recipesapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import coil.compose.AsyncImage


@Composable
fun SearchResultScreen(modifier: Modifier = Modifier, onMealClick : (id : String) -> Unit){
    val viewModel : SearchViewModel = viewModel()
    val searchResult = viewModel.searchResult.collectAsState().value
    val mealsList = searchResult.meals ?: emptyList()
    var text by remember() {mutableStateOf("") }
    Column(modifier = modifier.padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TextField(
            value = text,
            onValueChange = {cur -> text = cur},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {IconButton(onClick = {viewModel.searchRecipe(text)}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search recipe")
            }},
            placeholder = { Text("Search...") },
            singleLine = true
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
           items(items = mealsList ?: emptyList()){
               meal -> SearchResultCard(
               name = meal.strMeal ?: "Unknown",
               country = meal.strCountry ?: "Unknown",
               image = meal.strMealThumb ?: "Unknown",
               id = meal.idMeal ?: "",
               onCardClick = onMealClick)
           }
        }
    }

}
@Composable
fun SearchResultCard(modifier: Modifier = Modifier, name : String, country : String , image : String, id: String, onCardClick: (id: String) -> Unit){
    Card(modifier =modifier, onClick = {onCardClick(id)}) {
        Column() {
            AsyncImage(model = image, contentDescription = "mealImage", modifier = Modifier.height(200.dp).fillMaxWidth())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(name, style = MaterialTheme.typography.titleLarge)
                Text(country, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}