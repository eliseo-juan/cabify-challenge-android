package dev.eliseo.cabify.data.service

import dev.eliseo.cabify.data.dto.ProductResponse
import retrofit2.http.GET

interface ProductAPIService {

    @GET("products.json")
    suspend fun getProducts(): List<ProductResponse>

}