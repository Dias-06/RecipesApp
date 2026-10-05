package com.example.recipesapp.ui.search

import com.example.recipesapp.model.SearchedRecipeModel

sealed class SearchScreenUIState {
}
object Initial : SearchScreenUIState(){
}
object  Loading : SearchScreenUIState()
data class Success(val res : SearchedRecipeModel) : SearchScreenUIState()
data class Error(val errorMessage : String) : SearchScreenUIState()

object InCorrectSearch : SearchScreenUIState(){
    val message = "There is no meal with this name"
}