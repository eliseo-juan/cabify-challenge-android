package dev.eliseo.cabify.domain.usecase

import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.repository.DiscountRepository
import dev.eliseo.cabify.domain.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCartListUseCaseImplTest {

    private val discountRepository: DiscountRepository = mockk(relaxed = true)
    private val productRepository: ProductRepository = mockk(relaxed = true)
    private val cartRepository: CartRepository = mockk(relaxed = true)
    val useCase: GetCartListUseCaseImpl = GetCartListUseCaseImpl(
        discountRepository = discountRepository,
        productRepository = productRepository,
        cartRepository = cartRepository,
    )

    @Test
    fun `when discount is null, it returns the price x quantity`() {
        runBlocking {
            coEvery { productRepository.getProducts() } returns listOf(
                mockProduct(
                    code = "code1",
                    price = 50.0,
                ),
                mockProduct(
                    code = "code2",
                    price = 30.0,
                ),
                mockProduct(
                    code = "code3",
                    price = 40.0,
                )
            )
            coEvery { discountRepository.getDiscount(any()) } returns null
            coEvery { cartRepository.getCartProducts() } returns flow {
                emit(listOf("code1", "code1", "code1", "code2", "code3"))
            }
            val cartItemList = useCase().last()
            assertEquals(
                150.0f,
                cartItemList.first { it.product.code == "code1" }.finalPrice.toFloat()
            )
            assertEquals(
                30.0f,
                cartItemList.first { it.product.code == "code2" }.finalPrice.toFloat()
            )
            assertEquals(
                40.0f,
                cartItemList.first { it.product.code == "code3" }.finalPrice.toFloat()
            )
        }
    }

    @Test
    fun `when apply discount properly`() {
        runBlocking {
            coEvery { productRepository.getProducts() } returns listOf(
                mockProduct(
                    code = "VOUCHER",
                    price = 5.0,
                ),
                mockProduct(
                    code = "TSHIRT",
                    price = 20.0,
                ),
                mockProduct(
                    code = "MUG",
                    price = 7.5,
                )
            )
            coEvery { discountRepository.getDiscount("VOUCHER") } returns Discount.TakeXGetY(
                numberOfPaidProduts = 1,
                numberOfGifts = 1,
            )
            coEvery { discountRepository.getDiscount("TSHIRT") } returns Discount.DiscountByAmount(
                minNumberOfProducts = 3,
                discountPercentage = 0.05f,
            )
            coEvery { discountRepository.getDiscount("MUG") } returns null

            coEvery { cartRepository.getCartProducts() } returns flow {
                emit(listOf("VOUCHER", "TSHIRT", "MUG"))
            }

            assertEquals(32.5f, useCase().last().sumOf { it.finalPrice }.toFloat())

            coEvery { cartRepository.getCartProducts() } returns flow {
                emit(listOf("VOUCHER", "TSHIRT", "VOUCHER"))
            }

            assertEquals(25.0f, useCase().last().sumOf { it.finalPrice }.toFloat())

            coEvery { cartRepository.getCartProducts() } returns flow {
                emit(listOf("TSHIRT", "TSHIRT", "TSHIRT", "VOUCHER", "TSHIRT"))
            }

            assertEquals(81.0f, useCase().last().sumOf { it.finalPrice }.toFloat())

            coEvery { cartRepository.getCartProducts() } returns flow {
                emit(listOf("VOUCHER", "TSHIRT", "VOUCHER", "VOUCHER", "MUG", "TSHIRT", "TSHIRT"))
            }

            assertEquals(74.50f, useCase().last().sumOf { it.finalPrice }.toFloat())
        }
    }

    private fun mockProduct(
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