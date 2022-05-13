package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem

interface GetCartPriceUseCase {
    operator fun invoke(cartItems: List<CartItem>): Double
}