package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.repository.CartRepository
import javax.inject.Inject

class AddProductToCartUseCaseImpl @Inject constructor(
    val cartRepository: CartRepository
) : AddProductToCartUseCase {
    override suspend fun invoke(productId: String) {
        cartRepository.addProduct(productId)
    }
}