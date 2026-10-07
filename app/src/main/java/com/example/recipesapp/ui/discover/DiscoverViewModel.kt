package com.example.recipesapp.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import com.example.recipesapp.model.CategoryModel
import com.example.recipesapp.ui.discover.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {
    val categoryList = MutableStateFlow(CategoryModel(categories = emptyList()))
    val uiState = MutableStateFlow<DiscoverUIState>(Loading)

    init {
        getCategories()
    }
    fun getCategories(){
        uiState.value = Loading
        viewModelScope.launch {
            try {
                val res = Network.categoryApi.getCategories()
                categoryList.value = res
                uiState.value = Success(category = res)
            }catch (e : Exception){
                println(e.message)
            }
        }

    }
}