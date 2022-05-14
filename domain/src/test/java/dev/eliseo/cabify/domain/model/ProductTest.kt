package dev.eliseo.cabify.domain.model

object ProductTest {

    fun mockProduct(
        code: String = "code1",
        name: String = "Product 1",
        price: Double = 50.0,
        currencyCode: String = "EUR",
        imageUrl: String = "imageUrl"
    ) = Product(
        code = code,
        name = name,
        price = price,
        currencyCode = currencyCode,
        imageUrl = imageUrl,
    )
}