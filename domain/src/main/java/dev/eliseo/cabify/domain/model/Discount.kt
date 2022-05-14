package dev.eliseo.cabify.domain.model

sealed class Discount {
    data class DiscountByAmount(val minNumberOfProducts: Int, val discountPercentage: Float) :
        Discount()

    data class TakeXGetY(val numberOfPaidProduts: Int, val numberOfGifts: Int) : Discount()
}