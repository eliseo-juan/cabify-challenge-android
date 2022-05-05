package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetCartListUseCase {
    suspend fun execute(): Flow<List<Product>>
}