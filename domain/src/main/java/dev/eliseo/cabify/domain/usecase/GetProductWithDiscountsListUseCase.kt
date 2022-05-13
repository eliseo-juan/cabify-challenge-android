package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product

interface GetProductWithDiscountsListUseCase {
    suspend operator fun invoke(): Map<Product, Discount?>
}