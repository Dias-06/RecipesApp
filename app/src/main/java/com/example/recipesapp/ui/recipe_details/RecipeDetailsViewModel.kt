package com.example.recipesapp.ui.recipe_details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.RecipesApplication
import com.example.recipesapp.data.local.SavedMealEntity
import com.example.recipesapp.data.remote.Network
import com.example.recipesapp.model.getIngredientsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class RecipeDetailsViewModel(application: Application, savedStateHandle: SavedStateHandle) : AndroidViewModel(application = application) {
    private val _uiState = MutableStateFlow<DetailsUIState>(DetailsUIState.Loading)
    val uiState = _uiState.asStateFlow()
    private val db = (application as RecipesApplication).db
    private val _saved = MutableStateFlow(false)
    val saved = _saved.asStateFlow()
    init {
        val id = savedStateHandle["recipeId"] ?: ""
        getDetails(id)
        isSaved(id)
    }
    fun getDetails(id : String){
        viewModelScope.launch {
            try {
                _uiState.value = DetailsUIState.Loading
                val res = Network.searchByIdApi.searchById(id)
                val meal = res.meals?.firstOrNull()

                // 💡 2. Подробный лог
                Log.i("getDetails", "Успешно загружено блюдо: ${meal?.strMeal}")
                Log.d("getDetails", "Ингредиент 1: '${meal?.strIngredient1}', Мера 1: '${meal?.strMeasure1}'")
                Log.d("getDetails", "Всего ингредиентов распаршено: ${meal?.getIngredientsList()?.size}")
                _uiState.value = DetailsUIState.Success(res)
                Log.i("getDetails", "detailsSuccess")
            }catch (e : IOException){
                val message = "Check internet connection"
                _uiState.value= DetailsUIState.Error(message = message)
                println(e.message)
            }catch (e : Exception){
                val message = "Something went wrong"
                _uiState.value = DetailsUIState.Error(message = message)
                println(e.message)
            }
        }
    }
    fun saveRecipe(meal : SavedMealEntity){
        viewModelScope.launch {
            try {
                db.savedMealsDAO().addMeal(meal)
                Log.i("SaveMeal", "Meal saved successfully")
            }catch (e : Exception){
                Log.e("SaveMeal", "Error save meal",e)
            }
        }

    }
    fun isSaved(id : String){
        viewModelScope.launch {
            try {
                 _saved.value = db.savedMealsDAO().isMealSaved(id)
            }catch (e : Exception){
                Log.e("checkSavedMeal", "Error checking", e)
            }
        }
    }
}