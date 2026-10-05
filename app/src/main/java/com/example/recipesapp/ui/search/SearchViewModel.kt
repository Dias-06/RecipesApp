package com.example.recipesapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class SearchViewModel : ViewModel() {
    var uiState = MutableStateFlow<SearchScreenUIState>(Initial)
    var textInput = MutableStateFlow("")
    fun searchRecipe(text  : String){
        if (text == "") return
        viewModelScope.launch {
            try {
                uiState.value = Loading
                val res = Network.recipeApi.searchRecipes(text)
                if(res.meals == null){
                    uiState.value = InCorrectSearch
                }
                else uiState.value = Success(res)

            }catch (e : IOException){
                val message = "Check internet connection"
                 uiState.value = Error(message)
            }catch (e : Exception){
                val message =  "Something went wrong try again"
                uiState.value = Error(message)
            }
        }
    }
    fun inputTextChange(text: String){
        textInput.value = text
    }

}