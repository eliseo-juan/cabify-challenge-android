package dev.eliseo.cabify.feature.store.cart

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.usecase.GetCartListUseCase
import dev.eliseo.cabify.domain.usecase.GetCartPriceUseCase
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    val getCartListUseCase: GetCartListUseCase,
    val getCartPriceUseCase: GetCartPriceUseCase
) : BaseViewModel<CartViewModel.State, CartViewModel.Event>() {

    override fun createInitialState(): State = State()

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        getCartListUseCase().collect {
            setState {
                copy(
                    cart = it,
                    price = getCartPriceUseCase(it)
                )
            }
        }
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.OnItemClicked -> navigationManager.navigate(
                ProductDetailNavigation.productDetailDialog(productId = event.product.code)
            )
        }
    }

    data class State(
        val cart: List<CartItem> = emptyList(),
        val price: Double = 0.0,
        val currencyCode: String = "EUR"
    ) : UiState

    sealed class Event: UiEvent{
        data class OnItemClicked(val product: Product) : Event()
    }
}