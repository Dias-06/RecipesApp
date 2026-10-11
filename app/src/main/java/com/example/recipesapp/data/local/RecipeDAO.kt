package com.example.recipesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedMealsDAO{
    @Query("SELECT * FROM SavedMeals")
    suspend fun getSavedMeals() : List<SavedMealEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal : SavedMealEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM SavedMeals WHERE id = :id)")
    suspend fun isMealSaved(id : String) : Boolean
}