package com.example.recipesapp.data.local

import androidx.compose.ui.platform.LocalContext
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedMealEntity::class], version = 1)
abstract class Database() : RoomDatabase() {
    abstract fun savedMealsDAO() : SavedMealsDAO
}
