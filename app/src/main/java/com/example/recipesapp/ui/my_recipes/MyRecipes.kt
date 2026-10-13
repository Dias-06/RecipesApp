package com.example.recipesapp.ui.my_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyRecipes(){
    val viewModel : MyRecipesViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    when(uiState){
        is MyRecipesUIState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                            CircularProgressIndicator(modifier = Modifier.size(100.dp))
                                            }
        is MyRecipesUIState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.message,
                style = MaterialTheme.typography.titleLarge
            )
        }
        is MyRecipesUIState.Success ->
            Column(modifier = Modifier.padding(15.dp)) {
                uiState.res.forEach { recipe ->
                    Card() {
                        Row(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = recipe.name)
                            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "recipe details")
                        }
                    }
                }

            }
}
}