package dev.eliseo.cabify.feature.store

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.CheckoutNavigation
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCase
import dev.eliseo.cabify.core.presentation.base.BaseViewModel
import dev.eliseo.cabify.core.presentation.base.UiEvent
import dev.eliseo.cabify.core.presentation.base.UiState
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getProductWithDiscountsListUseCase: GetProductWithDiscountsListUseCase,
) : BaseViewModel<
        StoreViewModel.State,
        StoreViewModel.Event
        >() {

    override fun createInitialState(): State = State(
        isLoading = true
    )

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ProductClicked -> {
                navigationManager.navigate(
                    ProductDetailNavigation.productDetailDialog(productId = event.product.code)
                )
            }
            Event.OnGoToCheckout -> navigationManager.navigate(
                CheckoutNavigation.checkout
            )
        }
    }

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        val productsWithDiscounts = getProductWithDiscountsListUseCase()
        setState { copy(isLoading = false, products = productsWithDiscounts) }
    }

    data class State(
        val isLoading: Boolean = false,
        val products: Map<Product, Discount?> = emptyMap(),
    ) : UiState

    sealed class Event : UiEvent {
        data class ProductClicked(val product: Product) : Event()
        object OnGoToCheckout : Event()
    }
}