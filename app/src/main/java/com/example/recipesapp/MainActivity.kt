package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipesapp.ui.discover.Discover
import com.example.recipesapp.ui.recipe_details.RecipeDetails
import com.example.recipesapp.ui.search.SearchResultScreen
import com.example.recipesapp.ui.theme.RecipesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesAppTheme {
                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = { TopAppBar({Text("RecipeApp")}) }) { innerPadding ->
                        //Discover(modifier = Modifier.padding(innerPadding))
                    //SearchResultScreen(modifier = Modifier.padding(innerPadding))
                    RecipeDetails(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

