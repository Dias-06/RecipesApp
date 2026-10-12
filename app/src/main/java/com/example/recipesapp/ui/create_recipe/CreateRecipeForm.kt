package com.example.recipesapp.ui.create_recipe


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateRecipeForm(modifier: Modifier = Modifier){
    val text = remember { mutableStateOf("") }
    val textDescription = remember { mutableStateOf("") }
    val textInstruction = remember { mutableStateOf("") }
    val textIngredient = remember { mutableStateOf("") }
    val ingredientsList = remember { mutableStateListOf<String>() }
    Column(modifier = modifier.padding(horizontal = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        InputField(
            title = "Recipe name",
            value = text.value,
            onValueChange = {text.value = it},
            placeholder = "type name...")
        InputField(
            title = "Description",
            value = textDescription.value,
            onValueChange = {textDescription.value = it},
            placeholder = "type description...")
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            InputField(
                title = "Ingredients",
                value = textIngredient.value,
                onValueChange = {textIngredient.value = it},
                placeholder = "type ingredient...")
            Button(onClick = {ingredientsList.add(textIngredient.value)}, modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                    Text("Add ingredient")
                }
            }
            ingredientsList.forEachIndexed { index, string ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.Center) {
                    Text(text = string, style = MaterialTheme.typography.bodyLarge)
                    IconButton(onClick = {ingredientsList.removeAt(index)}) {
                        Icon(Icons.Default.Delete, contentDescription = "delete")
                    }
                }
            }
        }
        InputField(
            title = "Instructions",
            value = textInstruction.value,
            onValueChange = {textInstruction.value = it},
            placeholder = "type instruction...")
    }
}
@Composable
fun InputField(title : String, value : String, onValueChange : (String) -> Unit,placeholder : String){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {Text(placeholder)},
            modifier = Modifier.fillMaxWidth()
        )
    }
}