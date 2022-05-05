package dev.eliseo.cabify.data.mapper

import dev.eliseo.cabify.data.dto.ProductResponse
import dev.eliseo.cabify.data.fake.FakeDataProvider
import dev.eliseo.cabify.domain.model.Product

interface ProductMapper {
    fun ProductResponse.toProduct(): Product {
        return Product(
            code = code,
            name = name,
            description = (object : FakeDataProvider {}).getFakeDescription(code),
            price = price,
            currencyCode = (object : FakeDataProvider {}).getFakeCurrencyCode(code),
            imageUrl = (object : FakeDataProvider {}).getFakeImageUrl(code),
        )
    }
}