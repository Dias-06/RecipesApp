package com.example.recipesapp.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import com.example.recipesapp.model.CategoryModel
import com.example.recipesapp.ui.discover.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class DiscoverViewModel : ViewModel() {
    val uiState = MutableStateFlow<DiscoverUIState>(Loading)

    init {
        getCategories()
    }
    fun getCategories(){
        uiState.value = Loading
        viewModelScope.launch {
            try {
                val categories = Network.categoryApi.getCategories()
                val randomMeal = Network.randomMealApi.getRandomMeal()
                uiState.value = Success(category = categories, randomMeal)
            }catch (e : IOException){
                uiState.value = Error(message = "Check internet connection")
                println(e.message)
            }
        }

    }
}