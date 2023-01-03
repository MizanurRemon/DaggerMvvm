package com.example.daggermvvm.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.daggermvvm.API.APIService
import com.example.daggermvvm.Model.ProductsResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: APIService) {


    private val _products = MutableLiveData<List<ProductsResponse>>()

    val _productsData: LiveData<List<ProductsResponse>>
        get() = _products


    suspend fun getProducts() {
        val result = apiService.getProducts()

        if (result.isSuccessful && result.body() != null) {
            _products.postValue(result.body())
        }
    }
}