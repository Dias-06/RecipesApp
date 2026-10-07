package com.example.recipesapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipesapp.ui.MyRecipes
import com.example.recipesapp.ui.discover.Discover
import com.example.recipesapp.ui.recipe_details.RecipeDetails
import com.example.recipesapp.ui.saved.SavedRecipes
import com.example.recipesapp.ui.search.SearchResultScreen
import com.example.recipesapp.ui.searchByCategory.SearchByCategoryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController : NavHostController = rememberNavController()){
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar({Text("RecipeApp")})},
        bottomBar = { NavigationBar{
            NavigationBarItem(icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "discover") },
                onClick = {
                    navController.navigate("discover")},
                selected = currentRoute == "discover")
            NavigationBarItem(icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "saved") },
                onClick = {
                    navController.navigate("saved")},
                selected = currentRoute == "saved")
            NavigationBarItem(icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "my recipes") },
                onClick = {
                    navController.navigate("myRecipes")},
                selected = currentRoute == "myRecipes")
        }  })
    {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "discover",
            modifier = Modifier.padding(innerPadding))
        {
            composable(route = "discover") {
                Discover(
                    onSearchClick = {navController.navigate("searchResult")},
                    onCategoryClick = {name -> navController.navigate("searchByCategory/${name}")})
            }
            composable(route = "searchResult") {
                SearchResultScreen(onMealClick = {id -> navController.navigate("recipeDetails/${id}")})
            }
            composable("recipeDetails/{recipeId}") { backStackEntry ->
                RecipeDetails(recipeId = backStackEntry.arguments?.getString("recipeId") ?: "")
            }
            composable("saved") {
                SavedRecipes()
            }
            composable("myRecipes"){
                MyRecipes()
            }
            composable("searchByCategory/{mealName}"){ backStackEntry ->
                SearchByCategoryScreen(onMealClick = {id -> navController.navigate("recipeDetails/${id}")})
            }
        }
    }

}