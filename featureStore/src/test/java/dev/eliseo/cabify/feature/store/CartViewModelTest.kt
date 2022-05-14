package dev.eliseo.cabify.feature.store

import dev.eliseo.cabify.core.navigation.NavigationCommand
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.usecase.GetCartListUseCase
import dev.eliseo.cabify.domain.usecase.GetCartPriceUseCase
import dev.eliseo.cabify.domain.usecase.GetCartPriceUseCaseImpl
import dev.eliseo.cabify.feature.store.cart.CartViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
class CartViewModelTest {

    private val navigationManager: NavigationManager = mockk(relaxed = true)
    private val getCartListUseCase: GetCartListUseCase = mockk(relaxed = true)
    private val getCartPriceUseCase: GetCartPriceUseCase = GetCartPriceUseCaseImpl()
    val viewModel = CartViewModel(
        navigationManager = navigationManager,
        getCartListUseCase = getCartListUseCase,
        getCartPriceUseCase = getCartPriceUseCase
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when init it load cart state`() = runTest {
        coEvery { getCartListUseCase() } returns flow {
            emit(
                listOf(
                    CartItem(
                        product = mockProduct(
                            code = "code1",
                            price = 50.0,
                        ),
                        quantity = 2,
                        discount = null,
                        finalPrice = 90.0,
                        withoutDiscountPrice = 100.0
                    ),
                    CartItem(
                        product = mockProduct(
                            code = "code2",
                            price = 10.0,
                        ),
                        quantity = 3,
                        discount = null,
                        finalPrice = 20.0,
                        withoutDiscountPrice = 30.0
                    )
                )
            )
        }

        viewModel.init()

        assertEquals(110.0F, viewModel.currentState.price.toFloat())
        assertEquals(2, viewModel.currentState.cart.count())
    }

    @Test
    fun `when setEvent is OnItemClicked it calls to navigationManager`() = runTest {
        viewModel.setEvent(
            CartViewModel.Event.OnItemClicked(
                mockProduct(
                    code = "code1",
                    price = 50.0,
                )
            )
        )

        val slot = slot<NavigationCommand.Destination>()
        coVerify(exactly = 1) { navigationManager.navigate(capture(slot)) }
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