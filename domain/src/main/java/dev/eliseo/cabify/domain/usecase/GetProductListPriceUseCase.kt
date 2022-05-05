package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Product

interface GetProductListPriceUseCase {
    operator fun invoke(products: List<Product>): Double
}