package dev.eliseo.cabify.core.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun <State : UiState, Event : UiEvent> ViewModelScreen(
    viewModel: BaseViewModel<State, Event>,
    content: @Composable (State) -> Unit
) {
    val state: State by viewModel.uiState.collectAsState()
    content(state)
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.init()
    })
}