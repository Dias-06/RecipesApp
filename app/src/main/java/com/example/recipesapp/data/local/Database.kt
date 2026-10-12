package com.example.recipesapp.data.local

import androidx.compose.ui.platform.LocalContext
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SavedMealEntity::class, MyRecipeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database() : RoomDatabase() {
    abstract fun savedMealsDAO() : SavedMealsDAO // вернет готовый обьект реализующий этот интерфейс
    abstract fun myRecipesDAO() : MyRecipesDAO
}
