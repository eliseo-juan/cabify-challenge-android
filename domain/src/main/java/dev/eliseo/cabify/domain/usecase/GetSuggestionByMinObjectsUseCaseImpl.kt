package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Suggestion

class GetSuggestionByMinObjectsUseCaseImpl : GetSuggestionUseCase {
    override operator fun invoke(cartItem: List<CartItem>): Suggestion? {
        return cartItem.filter {
            it.discount != null
        }.map {
            Suggestion(
                product = it.product,
                numberOfProducts = numberOfExtraProductsToGetDiscount(it),
                discount = it.discount!!
            )

        }.filter {
            it.numberOfProducts > 0
        }.minByOrNull {
            it.numberOfProducts
        }
    }
}