package com.example.recipesapp.ui.recipe_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RecipeDetails(modifier: Modifier = Modifier){

    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        Box(modifier = Modifier.height(200.dp).fillMaxWidth().background(color = MaterialTheme.colorScheme.primary))//image
        Column() {
            Text("Teriyaki Chicken Casserole", style = MaterialTheme.typography.titleLarge)
            Text("Meat,Casserole")//tags
            Text("Ingredients: ", style = MaterialTheme.typography.titleMedium)
            Text("Instructions:", style = MaterialTheme.typography.titleMedium)
            Text("Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes.\\r\\nIn a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil.\\r\\nDrain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.")
        }

    }
}