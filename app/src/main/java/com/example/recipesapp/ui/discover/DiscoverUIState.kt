package com.example.recipesapp.ui.discover

import com.example.recipesapp.model.CategoryModel

sealed class DiscoverUIState
object Loading : DiscoverUIState()
data class Success(val category : CategoryModel) : DiscoverUIState()