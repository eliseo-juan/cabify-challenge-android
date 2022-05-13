package dev.eliseo.cabify.data.datasource

import dev.eliseo.cabify.domain.model.Discount

class NetworkDiscountDatasource {
    fun getDiscount(productCode: String): Discount? {
        return when (productCode) {
            "VOUCHER" -> Discount.TakeXGetY(2, 1)
            "TSHIRT" -> Discount.DiscountByAmount(3, 0.05f)
            else -> null
        }
    }
}