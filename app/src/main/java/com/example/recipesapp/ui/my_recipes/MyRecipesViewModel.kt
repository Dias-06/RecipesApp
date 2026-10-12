package com.example.recipesapp.ui.my_recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.RecipesApplication
import com.example.recipesapp.data.local.MyRecipeEntity
import kotlinx.coroutines.launch

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {
    val db = (application as RecipesApplication).db
    fun saveMyRecipe(recipe : MyRecipeEntity){
        viewModelScope.launch {
            try {
                db.myRecipesDAO().addMyRecipe(recipe)
                Log.i("SaveMyRecipe", "Recipe ${recipe.name} saved successfully")
            }catch (e : Exception){
                Log.e("SaveMyRecipe", "Error occurred while saving", e)
            }
        }
    }
}