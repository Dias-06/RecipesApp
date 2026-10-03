package com.example.recipesapp.ui.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.search.Meal
import com.example.recipesapp.ui.search.SearchResultCard


@Composable
fun SavedRecipes(modifier: Modifier = Modifier){
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
        ),)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier.padding(horizontal = 10.dp)) {
        items(meals){
            meal -> SearchResultCard(name = meal.strMeal, country = meal.strCountry, image = meal.strMealThumb)
        }
    }
}