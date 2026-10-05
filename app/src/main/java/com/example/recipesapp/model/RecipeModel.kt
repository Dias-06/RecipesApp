package com.example.recipesapp.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchedRecipeModel(
    val meals : List<Meal>? = emptyList()
)
@Serializable
data class Meal(
    val idMeal : String?,
    val strMeal : String?,
    val strCountry : String?,
    val strMealThumb : String?
)