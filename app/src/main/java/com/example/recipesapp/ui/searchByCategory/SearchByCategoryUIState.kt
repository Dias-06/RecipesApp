package com.example.recipesapp.ui.searchByCategory

import com.example.recipesapp.model.SearchedRecipeModel

sealed class SearchByCategoryUIState

object  Loading : SearchByCategoryUIState()
data class Success(val res : SearchedRecipeModel) : SearchByCategoryUIState()
data class Error(val errorMessage : String) : SearchByCategoryUIState()

