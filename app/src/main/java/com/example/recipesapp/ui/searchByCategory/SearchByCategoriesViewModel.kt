package com.example.recipesapp.ui.searchByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class SearchByCategoriesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val uiState = MutableStateFlow<SearchByCategoryUIState>(Loading)
    init {
        searchByCategory(savedStateHandle["mealName"] ?: "")
    }
    fun searchByCategory(categoryName: String){
        viewModelScope.launch {
            try {
                uiState.value = Loading
                val res = Network.searchByCategoryApi.searchByCategory(categoryName)
                uiState.value = Success(res)
            }catch (e : IOException){
                uiState.value = Error(errorMessage = "Check the internet connection")
            }catch (e : Exception){
                uiState.value = Error(errorMessage = "Something went wrong try again")
            }
        }
    }
}