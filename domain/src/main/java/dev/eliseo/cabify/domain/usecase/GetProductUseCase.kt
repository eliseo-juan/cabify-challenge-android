package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Product

interface GetProductUseCase {

    suspend operator fun invoke(productCode: String): Product?
}