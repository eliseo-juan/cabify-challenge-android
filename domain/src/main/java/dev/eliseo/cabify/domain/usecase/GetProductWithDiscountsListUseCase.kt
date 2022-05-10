package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductWithDiscountsListUseCase {
    suspend operator fun invoke(): Map<Product, Discount?>
}