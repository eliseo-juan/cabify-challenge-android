package dev.eliseo.cabify.domain.usecase

interface RemoveProductToCartUseCase {
    suspend operator fun invoke(productCode: String)
}