package dev.eliseo.cabify.domain.repository

import dev.eliseo.cabify.domain.model.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProduct(id: String): Product?

}