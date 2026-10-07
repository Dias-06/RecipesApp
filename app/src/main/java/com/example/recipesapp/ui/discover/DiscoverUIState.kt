package com.example.recipesapp.ui.discover

import com.example.recipesapp.model.CategoryModel
import com.example.recipesapp.model.SearchedRecipeModel

sealed class DiscoverUIState
object Loading : DiscoverUIState()
data class Error(val message : String) : DiscoverUIState()
data class Success(
    val category : CategoryModel,
    val randomMeal : SearchedRecipeModel
    ) : DiscoverUIState()