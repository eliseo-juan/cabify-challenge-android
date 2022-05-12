package dev.eliseo.cabify.domain.repository

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartProducts(): Flow<List<String>>

    suspend fun addProduct(productCode: String)

    suspend fun removeProduct(productCode: String)

}