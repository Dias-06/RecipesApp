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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Meal(val strMeal : String, val strCountry : String, val strMealThumb : String)

@Composable
fun SearchResultScreen(modifier: Modifier = Modifier, onMealClick : (id : Int) -> Unit){
    val meals = listOf(
        Meal(
            strMeal = "Plov",
            strCountry = "Uzbek",
            strMealThumb = "https://www.themealdb.com/images/media/meals/1529444830.jpg"
        ),
        Meal(
            strMeal = "Beshbarmak",
            strCountry = "Kazakh",
            strMealThumb = "https://www.themealdb.com/images/media/meals/beshbarmak.jpg"
        ),
        Meal(
            strMeal = "Spaghetti Carbonara",
            strCountry = "Italian",
            strMealThumb = "https://www.themealdb.com/images/media/meals/llc2011615762012.jpg"
        ),
        Meal(
            strMeal = "Chicken Tikka Masala",
            strCountry = "Indian",
            strMealThumb = "https://www.themealdb.com/images/media/meals/wyq2vl1601874207.jpg"
        ),
        Meal(
            strMeal = "Tacos",
            strCountry = "Mexican",
            strMealThumb = "https://www.themealdb.com/images/media/meals/uvuyxu1503067354.jpg"
        ),
        Meal(
            strMeal = "Sushi",
            strCountry = "Japanese",
            strMealThumb = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"
        ),
        Meal(
            strMeal = "Beef Stroganoff",
            strCountry = "Russian",
            strMealThumb = "https://www.themealdb.com/images/media/meals/svprys1511176755.jpg"
        ),
        Meal(
            strMeal = "Pad Thai",
            strCountry = "Thai",
            strMealThumb = "https://www.themealdb.com/images/media/meals/uuusmo1560463528.jpg"
        ),
        Meal(
            strMeal = "French Onion Soup",
            strCountry = "French",
            strMealThumb = "https://www.themealdb.com/images/media/meals/1529442352.jpg"
        ),
        Meal(
            strMeal = "Mousaka",
            strCountry = "Greek",
            strMealThumb = "https://www.themealdb.com/images/media/meals/ctg89i1606763070.jpg"
        )
    )
    var text by remember() {mutableStateOf("") }
    Column(modifier = modifier.padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TextField(
            value = text,
            onValueChange = {cur -> text = cur},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search recipe")
            }},
            placeholder = { Text("Search...") },
            singleLine = true
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(meals){
                meal -> SearchResultCard(name = meal.strMeal, country = meal.strCountry, image = meal.strMealThumb, onCardClick = onMealClick)
            }
        }
    }

}
@Composable
fun SearchResultCard(modifier: Modifier = Modifier, name : String, country : String, image : String, onCardClick: (id: Int) -> Unit){
    Card(modifier =modifier, onClick = {onCardClick(53083)}) {
        Column() {
            Box(modifier = Modifier.height(200.dp).fillMaxWidth().background(color = MaterialTheme.colorScheme.primary))//image
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(name, style = MaterialTheme.typography.titleLarge)
                Text(country, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}