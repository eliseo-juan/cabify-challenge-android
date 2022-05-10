package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import kotlinx.coroutines.flow.Flow

interface GetCartPriceUseCase {
    operator fun invoke(cartItems: List<CartItem>): Double
}