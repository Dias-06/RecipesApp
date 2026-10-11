package com.example.recipesapp

import android.app.Application
import androidx.room.Room
import com.example.recipesapp.data.local.Database

class RecipesApplication : Application() {
    val db by lazy {
        Room.databaseBuilder(
            this,
            Database::class.java,
            "recipesDatabase"
        ).build()
    }
}