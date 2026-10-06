package com.example.recipesapp.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.recipesapp.model.Category
import com.example.recipesapp.ui.theme.RecipesAppTheme


@Preview
@Composable fun DiscoverPreview(){
    RecipesAppTheme() {
        Discover(onSearchClick = {}, modifier = Modifier)
    }

}
@Composable fun Discover(modifier: Modifier = Modifier, onSearchClick : () -> Unit){
    val viewModel : DiscoverViewModel = viewModel()

    val categoryList = viewModel.categoryList.collectAsState().value.categories
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
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
            items(items = categoryList){
                category -> CategoryCard(categoryItem = category, modifier = Modifier.width(200.dp).height(220.dp))
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
@Composable
fun CategoryCard(modifier: Modifier = Modifier, categoryItem : Category){
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp)) {
            AsyncImage(model = categoryItem.strCategoryThumb,
                contentDescription = "category image",
                modifier = Modifier.fillMaxWidth().height(100.dp),
                contentScale = ContentScale.Crop)
            Text(textAlign = TextAlign.Center, text = categoryItem.strCategory, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))
            Text(text = categoryItem.strCategoryDescription, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }

}