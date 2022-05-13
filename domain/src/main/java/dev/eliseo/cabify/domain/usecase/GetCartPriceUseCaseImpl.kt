package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem

class GetCartPriceUseCaseImpl : GetCartPriceUseCase {
    override fun invoke(cartItems: List<CartItem>): Double {
        return cartItems.sumOf { it.finalPrice }
    }
}