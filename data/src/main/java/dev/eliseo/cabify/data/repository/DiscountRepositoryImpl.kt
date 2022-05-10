package dev.eliseo.cabify.data.repository

import dev.eliseo.cabify.data.datasource.NetworkDiscountDatasource
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.repository.DiscountRepository
import javax.inject.Inject

class DiscountRepositoryImpl @Inject constructor(
    private val discountDatasource: NetworkDiscountDatasource
) : DiscountRepository {

    override suspend fun getDiscount(productCode: String): Discount? {
        return discountDatasource.getDiscount(productCode)
    }
}