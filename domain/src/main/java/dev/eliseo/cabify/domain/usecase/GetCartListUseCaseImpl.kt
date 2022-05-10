package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartListUseCaseImpl @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : GetCartListUseCase {
    override suspend fun invoke(): Flow<List<CartItem>> {
        val products = productRepository.getProducts()
        return cartRepository.getCartProducts().map { productCodeList ->
            products.filter { productCodeList.contains(it.code) }
                .map { product ->
                    CartItem(
                        product = product,
                        quantity = productCodeList.count { it == product.code },
                        discount = discountRepository.getDiscount(product.code),
                    )
                }
        }

    }
}