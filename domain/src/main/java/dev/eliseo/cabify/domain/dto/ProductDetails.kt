package dev.eliseo.cabify.domain.dto

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product

data class ProductDetails(
    val product: Product,
    val quantity: Int,
    val discount: Discount?,
)