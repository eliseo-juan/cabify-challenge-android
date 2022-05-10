package dev.eliseo.cabify.domain.repository

import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartProducts(): Flow<List<String>>

    suspend fun addProduct(product: Product)

    suspend fun removeProduct(product: Product)

}