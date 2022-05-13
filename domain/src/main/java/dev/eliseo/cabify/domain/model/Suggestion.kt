package dev.eliseo.cabify.domain.model

data class Suggestion(
    val product: Product,
    val numberOfProducts: Int,
    val discount: Discount
)