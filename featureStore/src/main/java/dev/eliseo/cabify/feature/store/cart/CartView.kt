package dev.eliseo.cabify.feature.store.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.eliseo.cabify.core.ds.Divider
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.core.presentation.view.CartItemView
import dev.eliseo.cabify.core.presentation.view.SummaryView
import dev.eliseo.cabify.store.libbase.ViewModelScreen

@Composable
fun CartView(
    viewModel: CartViewModel
) {
    ViewModelScreen(
        viewModel = viewModel
    ) { state ->
        CartView(state) {
            viewModel.setEvent(it)
        }
    }
}

@Composable
fun CartView(
    state: CartViewModel.State,
    event: (CartViewModel.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SummaryView(
            modifier = Modifier
                .height(64.dp)
                .padding(horizontal = 16.dp),
            price = with(object : CurrencyRetriever {}) {
                state.price.getWhitCurrencyFormat()
            }
        )
        Divider()
        LazyColumn(
            contentPadding =
            if (state.cart.isEmpty()) PaddingValues(0.dp)
            else PaddingValues(8.dp)
        ) {
            items(state.cart) { item ->
                CartItemView(
                    modifier = Modifier.clickable {
                        event(CartViewModel.Event.OnItemClicked(product = item.product))
                    },
                    item
                )
            }
        }
    }
}

@Composable
@Preview
fun CartViewPreview() {
    CartView(
        state = CartViewModel.State(
            cart = emptyList(),
            price = 0.0
        )
    ) {

    }
}