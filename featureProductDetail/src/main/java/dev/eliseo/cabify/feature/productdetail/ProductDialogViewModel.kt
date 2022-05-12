package dev.eliseo.cabify.feature.productdetail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation.KEY_PRODUCT_ID
import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.domain.usecase.*
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject

@HiltViewModel
class ProductDialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val getCartListItemUseCase: GetCartListItemUseCase,
    val addProductToCartUseCase: AddProductToCartUseCase,
    val removeProductToCartUseCase: RemoveProductToCartUseCase,
) : BaseViewModel<
        ProductDialogViewModel.State,
        ProductDialogViewModel.Event,
        >() {

    override fun createInitialState(): State = State()

    private val productId: String
        get() = savedStateHandle.get<String>(KEY_PRODUCT_ID) ?: throw IllegalStateException("Product id not found")

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        getCartListItemUseCase(productId).collect {
            setState { copy(cartItem = it) }
        }
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.AddProduct -> addProductToCartUseCase(productId)
            is Event.RemoveProduct -> removeProductToCartUseCase(productId)
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val cartItem: CartItem? = null,
    ) : UiState

    sealed class Event : UiEvent {
        object AddProduct : Event()
        object RemoveProduct : Event()
    }
}