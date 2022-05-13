package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository

class GetProductWithDiscountsListUseCaseImpl(
    private val productRepository: ProductRepository,
    private val discountRepository: DiscountRepository
) : GetProductWithDiscountsListUseCase {

    override suspend fun invoke(): Map<Product, Discount?> {
        return productRepository.getProducts().associateWith {
            discountRepository.getDiscount(it.code)
        }
    }
}