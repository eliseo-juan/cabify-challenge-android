package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.ProductDetails
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartListItemUseCaseImpl @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : GetCartListItemUseCase {
    override suspend fun invoke(productCode: String): Flow<ProductDetails?> {
        val products = productRepository.getProducts()
        return cartRepository
            .getCartProducts()
            .map { cartProductCodeList ->
                products.firstOrNull { it.code == productCode }?.let { product ->
                    val quantity = cartProductCodeList.count { it == product.code }
                    val discount = discountRepository.getDiscount(product.code)
                    ProductDetails(
                        product = product,
                        quantity = quantity,
                        discount = discount,
                    )
                }
            }
    }
}