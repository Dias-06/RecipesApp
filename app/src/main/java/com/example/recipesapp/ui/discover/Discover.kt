package com.example.recipesapp.ui.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable fun Discover(modifier: Modifier = Modifier){
    var text by remember() {mutableStateOf<String>("") }
    Column(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {cur -> text = cur},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search recipe")
            }},
            placeholder = { Text("Search...") },
            singleLine = true
        )

        Column() {
            Text(text = "Categories", style = MaterialTheme.typography.titleMedium)
            val scrollState = rememberScrollState()
            val categoryList = listOf<String>("Breakfast", "Lunch", "Dinner", "Dessert")
            Row() {
                categoryList.forEach { cat -> Text(text = cat, style = MaterialTheme.typography.bodyMedium) }
            }
        }
    }


}