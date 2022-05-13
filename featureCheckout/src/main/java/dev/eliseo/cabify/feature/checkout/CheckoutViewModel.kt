package dev.eliseo.cabify.feature.checkout

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.CheckoutNavigation
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.model.Suggestion
import dev.eliseo.cabify.domain.usecase.AddProductToCartUseCase
import dev.eliseo.cabify.domain.usecase.GetCartListUseCase
import dev.eliseo.cabify.domain.usecase.GetCartPriceUseCase
import dev.eliseo.cabify.domain.usecase.GetSuggestionUseCase
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    val getCartListUseCase: GetCartListUseCase,
    val getCartPriceUseCase: GetCartPriceUseCase,
    val getSuggestionUseCase: GetSuggestionUseCase,
    val addProductToCartUseCase: AddProductToCartUseCase
) : BaseViewModel<CheckoutViewModel.State, CheckoutViewModel.Event>() {

    override fun createInitialState(): State = State()

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        getCartListUseCase().collect {
            setState {
                copy(
                    cartItems = it,
                    price = getCartPriceUseCase(it),
                    suggestion = getSuggestionUseCase(it)
                )
            }
        }
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.OnItemClicked -> navigationManager.navigate(
                ProductDetailNavigation.productDetailDialog(event.product.code)
            )
            is Event.OnSuggestionAddClicked -> {
                addProductToCartUseCase.invoke(event.suggestion.product.code, event.suggestion.numberOfProducts)
            }
        }
    }

    data class State(
        val cartItems: List<CartItem> = emptyList(),
        val price: Double = 0.0,
        val suggestion: Suggestion? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class OnItemClicked(val product: Product) : Event()
        data class OnSuggestionAddClicked(val suggestion: Suggestion) : Event()
    }

}