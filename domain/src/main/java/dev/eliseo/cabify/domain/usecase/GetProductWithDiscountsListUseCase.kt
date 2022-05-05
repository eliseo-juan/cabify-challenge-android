package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductWithDiscountsListUseCase {
    operator fun invoke(): Flow<Map<Product, Discount?>>
}