package dev.eliseo.cabify.store.libbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import javax.inject.Inject

abstract class BaseActivity<
        State : UiState,
        Event : UiEvent,
        VIEW_MODEL : BaseViewModel<State, Event>> : ComponentActivity() {

    protected abstract val viewModel: VIEW_MODEL

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewState by viewModel.uiState.collectAsState()
            themeProvider.ApplyTheme { RenderViewState(viewState) }
        }
    }

    @Composable
    protected abstract fun RenderViewState(state: State)

}