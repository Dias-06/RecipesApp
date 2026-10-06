package com.example.recipesapp.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.data.remote.Network
import com.example.recipesapp.model.CategoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {
    init {
        getCategories()
    }
    val categoryList = MutableStateFlow(CategoryModel(categories = emptyList()))
    fun getCategories(){
        viewModelScope.launch {
            try {
                val res = Network.categoryApi.getCategories()
                categoryList.value = res
            }catch (e : Exception){
                println(e.message)
            }
        }

    }
}