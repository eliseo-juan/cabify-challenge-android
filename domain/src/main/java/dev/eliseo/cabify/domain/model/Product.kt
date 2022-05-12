package dev.eliseo.cabify.domain.model

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val currencyCode: String,
    val imageUrl: String
)