package dev.eliseo.cabify.domain.usecase

interface AddProductToCartUseCase {
    suspend operator fun invoke(productId: String, quantity: Int = 1)
}