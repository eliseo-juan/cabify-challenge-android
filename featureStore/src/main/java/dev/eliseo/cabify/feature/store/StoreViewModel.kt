package dev.eliseo.cabify.feature.store

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.domain.usecase.GetProductWithDiscountsListUseCaseImpl
import dev.eliseo.cabify.store.libbase.BaseViewModel
import dev.eliseo.cabify.store.libbase.UiEffect
import dev.eliseo.cabify.store.libbase.UiEvent
import dev.eliseo.cabify.store.libbase.UiState
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(

) : BaseViewModel<
        StoreViewModel.State,
        StoreViewModel.Event,
        StoreViewModel.Effect,
        >() {

    override fun createInitialState(): State = State(
        isLoading = true
    )

    override suspend fun extraInitializationSteps() {
        super.extraInitializationSteps()
        val getProductWithDiscountsListUseCase = GetProductWithDiscountsListUseCaseImpl()
        getProductWithDiscountsListUseCase().collect{
            setState { copy(isLoading = false, products = it) }
        }
    }

    override suspend fun handleEvent(event: Event) {
        TODO("Not yet implemented")
    }

    data class State(
        val isLoading: Boolean = false,
        val products: Map<Product, Discount?> = emptyMap(),
    ) : UiState

    sealed class Event : UiEvent {}

    sealed class Effect : UiEffect {}
}