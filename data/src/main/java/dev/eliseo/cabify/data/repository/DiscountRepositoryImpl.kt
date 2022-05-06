package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.repository.DiscountRepository

class DiscountRepositoryImpl : DiscountRepository {

    override suspend fun getDiscounts(): List<Discount> {
        TODO("Not yet implemented")
    }

    override suspend fun getDiscount(productCode: String): Discount? {
        TODO("Not yet implemented")
    }
}