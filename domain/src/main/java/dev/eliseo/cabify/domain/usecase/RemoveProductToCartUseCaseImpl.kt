package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.repository.CartRepository
import javax.inject.Inject

class RemoveProductToCartUseCaseImpl @Inject constructor(
    val cartRepository: CartRepository
) : RemoveProductToCartUseCase {
    override suspend fun invoke(productCode: String) {
        cartRepository.removeProduct(productCode)
    }
}