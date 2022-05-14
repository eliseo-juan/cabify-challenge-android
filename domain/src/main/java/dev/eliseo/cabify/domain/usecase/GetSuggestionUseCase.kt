package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Suggestion

interface GetSuggestionUseCase {
    operator fun invoke(cartItem: List<CartItem>): Suggestion?

    fun numberOfExtraProductsToGetDiscount(cartItem: CartItem): Int {
        return when (cartItem.discount) {
            is Discount.DiscountByAmount -> {
                cartItem.discount.minNumberOfProducts - cartItem.quantity
            }
            is Discount.TakeXGetY -> {
                cartItem.discount.numberOfPaidProduts + cartItem.discount.numberOfGifts - cartItem.quantity
            }
            null -> 0
        }
    }
}