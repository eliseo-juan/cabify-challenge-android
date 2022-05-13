package dev.eliseo.cabify.feature.checkout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.ds.Divider
import dev.eliseo.cabify.core.ds.TopBar
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.core.presentation.view.CartItemView
import dev.eliseo.cabify.core.presentation.view.DiscountContainerView
import dev.eliseo.cabify.core.presentation.view.SummaryView
import dev.eliseo.cabify.domain.model.Suggestion
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
        bottomBar = {
            SummaryView(
                modifier = Modifier
                    .defaultMinSize(minHeight = 64.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = 16.dp),
                price = with(object : CurrencyRetriever {}) {
                    state.price.getWhitCurrencyFormat()
                }
            )
        }
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {

            Column(
                Modifier
                    .padding(8.dp)
            ) {
                state.cartItems.forEach { item ->
                    CartItemView(
                        modifier = Modifier.clickable {
                            event(CheckoutViewModel.Event.OnItemClicked(product = item.product))
                        },
                        item = item
                    )
                }
            }
            if (state.suggestion != null) {
                SuggestionView(suggestion = state.suggestion) {
                    event(CheckoutViewModel.Event.OnSuggestionAddClicked(suggestion = state.suggestion))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SuggestionView(
    modifier: Modifier = Modifier,
    suggestion: Suggestion,
    onAddClick: () -> Unit
) {
    Column(
        modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Reviews,
                contentDescription = "",
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(
                    id = R.string.checkout_suggestion,
                    suggestion.numberOfProducts
                ),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.caption
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(40.dp),
                model = suggestion.product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "x${suggestion.numberOfProducts}",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = suggestion.product.name,
                style = MaterialTheme.typography.body1
            )
            Spacer(
                modifier = Modifier
                    .weight(1f, true)
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = with(object : CurrencyRetriever {}) {
                        suggestion.product.price.getWhitCurrencyFormat()
                    },
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onAddClick()
            }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.checkout_suggestion_add),
                style = MaterialTheme.typography.button
            )
        }
    }
}