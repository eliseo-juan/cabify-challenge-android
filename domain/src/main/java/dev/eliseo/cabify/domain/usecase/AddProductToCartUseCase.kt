package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Product

interface AddProductToCartUseCase {
    suspend operator fun invoke(product: Product)
}