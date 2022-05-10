package dev.eliseo.cabify.feature.store

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.repository.CartRepository
import dev.eliseo.cabify.domain.usecase.GetProductUseCase
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCase
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCaseImpl
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEffect
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getProductWithDiscountsListUseCase: GetProductWithDiscountsListUseCase,
    private val cartRepository: CartRepository,
) : BaseViewModel<
        StoreViewModel.State,
        StoreViewModel.Event
        >() {

    override fun createInitialState(): State = State(
        isLoading = true
    )

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.OnLoaded -> extraInitializationSteps()
            is Event.ProductClicked -> {
                cartRepository.addProduct(event.product)
                navigationManager.navigate(
                    ProductDetailNavigation.productDetailDialog(productId = event.product.code)
                )
            }
        }
    }

    private suspend fun extraInitializationSteps() {
        val productsWithDiscounts = getProductWithDiscountsListUseCase()
        setState { copy(isLoading = false, products = productsWithDiscounts) }
    }

    data class State(
        val isLoading: Boolean = false,
        val products: Map<Product, Discount?> = emptyMap(),
    ) : UiState

    sealed class Event : UiEvent {
        object OnLoaded : Event()
        data class ProductClicked(val product: Product) : Event()
        object OnGoToCheckout : Event()
    }
}