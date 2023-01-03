package com.example.daggermvvm.API

import com.example.daggermvvm.Model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {


    @GET("products")
    suspend fun getProducts(): Response<List<ProductsResponse>>
}