package com.example.recipesapp.ui.recipe_details

import com.example.recipesapp.model.SearchedRecipeModel

sealed class DetailsUIState {
    object Loading : DetailsUIState()
    data class Success(val res : SearchedRecipeModel) : DetailsUIState()
    data class Error(val message : String): DetailsUIState()
}