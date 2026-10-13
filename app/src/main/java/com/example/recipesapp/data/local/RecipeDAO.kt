package com.example.recipesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedMealsDAO{
    @Query("SELECT * FROM SavedMeals")
    suspend fun getSavedMeals() : List<SavedMealEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal : SavedMealEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM SavedMeals WHERE id = :id)")
    suspend fun isMealSaved(id : String) : Boolean

    @Delete
    suspend fun deleteMeal(meal : SavedMealEntity)
}
@Dao
interface MyRecipesDAO{
    @Query("SELECT * FROM MyRecipes")
    suspend fun getMyRecipes() : List<MyRecipeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMyRecipe(recipe : MyRecipeEntity)
}