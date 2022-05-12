package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartListItemUseCaseImpl @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : GetCartListItemUseCase {
    override suspend fun invoke(productCode: String): Flow<CartItem?> {
        val products = productRepository.getProducts()
        return cartRepository
            .getCartProducts()
            .map { cartProductCodeList ->
                products.firstOrNull { it.code == productCode }?.let { product ->
                    CartItem(
                        product = product,
                        quantity = cartProductCodeList.count { it == product.code },
                        discount = discountRepository.getDiscount(product.code),
                    )
                }
            }
    }
}