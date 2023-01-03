package com.example.daggermvvm.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvm.Model.ProductsResponse
import com.example.daggermvvm.Repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {
    val productsLiveData: LiveData<List<ProductsResponse>>
        get() = repository._productsData

    init {
        viewModelScope.launch { repository.getProducts() }
    }
}