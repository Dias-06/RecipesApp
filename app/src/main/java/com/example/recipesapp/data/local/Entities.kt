package com.example.recipesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedMeals")
data class SavedMealEntity(
    @PrimaryKey val id : String ,
    val name : String? = "Unknown",
    val image : String? = "Unknown",
    val country : String? = "Unknown"
)