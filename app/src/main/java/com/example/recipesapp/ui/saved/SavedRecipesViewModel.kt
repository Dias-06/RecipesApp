package com.example.recipesapp.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.RecipesApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(application: Application) : AndroidViewModel(application = application) {
    val db = (application as RecipesApplication).db
    private val _uiState = MutableStateFlow<SavedRecipesUIState>(SavedRecipesUIState.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        getMeals()
    }
    fun getMeals(){
        viewModelScope.launch {
            try {
                _uiState.value = SavedRecipesUIState.Loading
                val meals =db.savedMealsDAO().getSavedMeals()
                Log.i("getSavedMeals", "Meals receive successful $meals")
                _uiState.value = SavedRecipesUIState.Success(res= meals)
            }catch (e : Exception){
                Log.e("getSavedMeals", "Error getting saved meals", e)
                _uiState.value = SavedRecipesUIState.Error("Error")
            }
        }
    }
}