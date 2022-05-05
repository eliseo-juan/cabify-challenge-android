package dev.eliseo.cabify.store.libbase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun <State : UiState, Event : UiEvent, Effect : UiEffect> ViewModelScreen(
    viewModel: BaseViewModel<State, Event, Effect>,
    effect: (Effect) -> Unit,
    content: @Composable (State) -> Unit
) {
    val viewState: State by viewModel.uiState.collectAsState()
    content(viewState)
}