package dev.eliseo.cabify.feature.checkout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.eliseo.cabify.core.ds.Divider
import dev.eliseo.cabify.core.ds.TopBar
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.core.presentation.view.CartItemView
import dev.eliseo.cabify.core.presentation.view.SummaryView
import dev.eliseo.cabify.store.libbase.ViewModelScreen

@Composable
fun CheckoutView(
    viewModel: CheckoutViewModel
) {
    ViewModelScreen(
        viewModel = viewModel
    ) { state ->
        CheckoutView(state) { event ->
            viewModel.setEvent(event)
        }
    }
}

@Composable
fun CheckoutView(
    state: CheckoutViewModel.State,
    event: (CheckoutViewModel.Event) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.checkout_title))
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.checkout_cta_pay))
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Payment,
                        contentDescription = null
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                onClick = {

                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        Column(
            Modifier
                .padding(
                    if (state.cartItems.isEmpty()) PaddingValues(0.dp)
                    else PaddingValues(8.dp)
                )

        ) {
            state.cartItems.forEach { item ->
                CartItemView(
                    modifier = Modifier.clickable {
                        event(CheckoutViewModel.Event.OnItemClicked(product = item.product))
                    },
                    item = item
                )
            }
            Divider(Modifier.padding(8.dp))
            SummaryView(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                price = with(object : CurrencyRetriever {}) {
                    state.price.getWhitCurrencyFormat()
                }
            )
        }
    }
}