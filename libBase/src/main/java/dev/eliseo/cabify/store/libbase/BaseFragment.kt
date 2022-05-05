package dev.eliseo.cabify.store.libbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import javax.inject.Inject

abstract class BaseFragment<
        State : UiState,
        Event : UiEvent,
        Effect : UiEffect,
        ViewModel : BaseViewModel<State, Event, Effect>> : Fragment() {

    @Inject
    lateinit var themeProvider: ThemeProvider

    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                handleEffect(it)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewState by viewModel.uiState.collectAsState()
                themeProvider.ApplyTheme { RenderViewState(viewState) }
            }
        }
    }

    @Composable
    protected abstract fun RenderViewState(state: State)

    protected abstract fun handleEffect(effect: Effect)
}