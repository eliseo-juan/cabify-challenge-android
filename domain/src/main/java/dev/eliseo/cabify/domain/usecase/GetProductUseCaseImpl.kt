package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductUseCase {
    
    override suspend fun invoke(productCode: String): Product? {
        return productRepository.getProduct(productCode)
    }
}