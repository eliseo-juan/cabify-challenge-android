package dev.eliseo.cabify.store.libbase

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import javax.inject.Inject

abstract class BaseActivity<
        State : UiState,
        Event : UiEvent,
        Effect : UiEffect,
        VIEW_MODEL : BaseViewModel<State, Event, Effect>> : ComponentActivity() {

    protected abstract val viewModel: VIEW_MODEL

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewState by viewModel.uiState.collectAsState()
            themeProvider.ApplyTheme { RenderViewState(viewState) }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                handleEffect(it)
            }
        }
    }

    @Composable
    protected abstract fun RenderViewState(state: State)

    protected abstract fun handleEffect(effect: Effect)

}