package dev.eliseo.cabify.store.ui.product_dialog

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.usecase.GetProductUseCase
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEffect
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject



@HiltViewModel
class ProductDialogViewModel @Inject constructor(
    val getProductUseCase: GetProductUseCase
) : BaseViewModel<
        ProductDialogViewModel.State,
        ProductDialogViewModel.Event,
        ProductDialogViewModel.Effect,
        >() {

    override fun createInitialState(): State = State()

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        val product = getProductUseCase("productCode")
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.AddProduct -> TODO()
            is Event.RemoveProduct -> TODO()
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val product: Product? = null,
        val discount: Discount? = null,
        val cartCount: Int = 0,
    ) : UiState

    sealed class Event : UiEvent {
        data class AddProduct(val product: Product) : Event()
        data class RemoveProduct(val product: Product) : Event()
    }

    sealed class Effect : UiEffect {
        object Close : Effect()
    }
}