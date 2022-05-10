package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem

class GetCartPriceUseCaseImpl : GetCartPriceUseCase {
    override fun invoke(cartItems: List<CartItem>): Double {
        return cartItems.fold(0.0) { acc, cartItem ->
            acc + (cartItem.product.price * cartItem.quantity)
        }
    }
}