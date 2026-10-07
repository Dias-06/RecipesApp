package com.example.recipesapp.ui.searchByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException

class SearchByCategoriesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchByCategoryUIState>(Loading)
    val uiState = _uiState.asStateFlow()
    init {
        searchByCategory(savedStateHandle["mealName"] ?: "")
    }
    fun searchByCategory(categoryName: String){
        viewModelScope.launch {
            try {
                _uiState.value = Loading
                val res = Network.searchByCategoryApi.searchByCategory(categoryName)
                _uiState.value = Success(res)
            }catch (e : IOException){
                _uiState.value = Error(errorMessage = "Check the internet connection")
            }catch (e : Exception){
                _uiState.value = Error(errorMessage = "Something went wrong try again")
            }
        }
    }
}