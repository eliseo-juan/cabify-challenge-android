package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
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
                    val quantity = productCodeList.count { it == product.code }
                    val discount = discountRepository.getDiscount(product.code)
                    val prices =
                        getPrices(product = product, quantity = quantity, discount = discount)
                    CartItem(
                        product = product,
                        quantity = quantity,
                        discount = discount,
                        finalPrice = prices.finalPrice,
                        withoutDiscountPrice = prices.withoutDiscountPriceIfDifferent
                    )
                }
        }
    }

    private fun getPrices(
        product: Product,
        quantity: Int,
        discount: Discount?
    ): CartItemPrice {
        val withoutDiscountPrice = product.price * quantity
        val totalPrice = discount?.let {
            getPriceWithDiscount(product, quantity, it)
        } ?: withoutDiscountPrice
        return CartItemPrice(totalPrice, withoutDiscountPrice)

    }

    private fun getPriceWithDiscount(
        product: Product,
        quantity: Int,
        discount: Discount
    ): Double {
        return when (discount) {
            is Discount.DiscountByAmount -> {
                if (quantity >= discount.minNumberOfProducts) {
                    product.price * quantity * (1 - discount.discountPercentage)
                } else {
                    product.price * quantity
                }
            }
            is Discount.TakeXGetY -> {
                val blockSize: Int = (discount.numberOfPaidProduts + discount.numberOfGifts)
                val numberOfBlocks: Int = quantity / blockSize
                val rest = quantity % blockSize
                return numberOfBlocks * product.price * discount.numberOfPaidProduts + rest * product.price
            }
        }
    }

    private data class CartItemPrice(
        val finalPrice: Double,
        val withoutDiscountPrice: Double
    ) {
        val withoutDiscountPriceIfDifferent: Double?
            get() = if (finalPrice != withoutDiscountPrice) withoutDiscountPrice else null
    }
}