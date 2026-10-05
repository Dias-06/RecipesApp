package com.example.recipesapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import com.example.recipesapp.model.SearchedRecipeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val searchResult = MutableStateFlow<SearchedRecipeModel>(SearchedRecipeModel())
    fun searchRecipe(text  : String){
        viewModelScope.launch {
            val res = Network.recipeApi.searchRecipes(text)
            searchResult.value = res
        }
    }

}