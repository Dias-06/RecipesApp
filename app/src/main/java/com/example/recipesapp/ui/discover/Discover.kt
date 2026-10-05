package com.example.recipesapp.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable fun Discover(modifier: Modifier = Modifier, onSearchClick : () -> Unit){
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState).padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp) ) {
        //input for search recipe
        TextField(
            readOnly = true,
            value = "",
            enabled = false,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().clickable(onClick = {onSearchClick()}),
            trailingIcon = {IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search recipe")
            }},
            placeholder = { Text("Search...") },
            singleLine = true
        )
        //categories
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(text = "Categories", style = MaterialTheme.typography.titleMedium)
            val scrollState = rememberScrollState()
            val categoryList = listOf<String>("Breakfast", "Lunch", "Dinner", "Dessert")
            Row(modifier = Modifier.horizontalScroll(scrollState), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                categoryList.forEach { cat -> Button(onClick = {}) {
                    Text(text = cat, style = MaterialTheme.typography.bodyMedium)}
                }
            }
        }
        //Random recipe
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()) {
                Text(text = "Random recipe", style = MaterialTheme.typography.titleMedium)
                Box(modifier = Modifier.size(200.dp).background(color = MaterialTheme.colorScheme.primary))//image
                Button(onClick = {}) { //name and navigation
                    Text("Chicken curry")
                }
        }
    }


}