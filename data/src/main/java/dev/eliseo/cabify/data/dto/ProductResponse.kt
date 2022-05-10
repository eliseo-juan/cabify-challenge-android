package dev.eliseo.cabify.data.dto

data class ProductListResponse(
    val products: List<ProductResponse>
)

data class ProductResponse(
    val code: String,
    val name: String,
    val price: Double
)
