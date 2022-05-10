package dev.eliseo.cabify.domain.repository

import dev.eliseo.cabify.domain.model.Discount

interface DiscountRepository {

    suspend fun getDiscount(productCode: String): Discount?
}