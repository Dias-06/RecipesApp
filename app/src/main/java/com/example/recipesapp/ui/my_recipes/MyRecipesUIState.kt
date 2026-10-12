package com.example.recipesapp.ui.my_recipes

import com.example.recipesapp.data.local.MyRecipeEntity

sealed class MyRecipesUIState {
    object  Loading : MyRecipesUIState()
    data class Success(val res : List<MyRecipeEntity>) : MyRecipesUIState()
    data class Error(val message : String) : MyRecipesUIState()
}