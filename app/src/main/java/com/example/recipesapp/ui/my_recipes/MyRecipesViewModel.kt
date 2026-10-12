package com.example.recipesapp.ui.my_recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.RecipesApplication
import com.example.recipesapp.data.local.MyRecipeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow<MyRecipesUIState>(MyRecipesUIState.Loading)
    val uiState = _uiState.asStateFlow()
    val db = (application as RecipesApplication).db
    init {
        viewModelScope.launch {
            try {
                val res = db.myRecipesDAO().getMyRecipes()
                Log.i("GetMyRecipes", "${res.size} recipes arrived successfully")
                _uiState.value = MyRecipesUIState.Success(res)
            }catch (e : Exception){
                _uiState.value = MyRecipesUIState.Error("Something went wrong")
                Log.e("GetMyRecipes", "Error occurred while observing recipes", e)
            }
        }
    }
}