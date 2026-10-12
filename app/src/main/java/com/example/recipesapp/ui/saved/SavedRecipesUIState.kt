package com.example.recipesapp.ui.saved

import com.example.recipesapp.data.local.SavedMealEntity

sealed class SavedRecipesUIState {
    object Loading : SavedRecipesUIState()
    data class Success(val res : List<SavedMealEntity>) : SavedRecipesUIState()
    data class Error(val message : String) : SavedRecipesUIState()

}