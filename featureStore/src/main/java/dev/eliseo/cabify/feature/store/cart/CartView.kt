package dev.eliseo.cabify.feature.store.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.ds.Divider
import dev.eliseo.cabify.domain.dto.CartItem
import dev.eliseo.cabify.store.libbase.ViewModelScreen
import java.text.NumberFormat
import java.util.*

@Composable
fun CartView(
    viewModel: CartViewModel
) {
    ViewModelScreen(
        viewModel = viewModel,
        onInitialized = {
            viewModel.setEvent(CartViewModel.Event.OnLoaded)
        }
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
        CartHeaderView(
            modifier = Modifier
                .height(64.dp),
            state
        )
        Divider()
        LazyColumn(
            contentPadding = PaddingValues(8.dp)
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
fun CartHeaderView(
    modifier: Modifier = Modifier,
    state: CartViewModel.State
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = NumberFormat.getCurrencyInstance().also {
                it.currency = Currency.getInstance(state.currencyCode)
            }.format(state.price),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun CartItemView(
    modifier: Modifier = Modifier,
    item: CartItem
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(40.dp),
            model = item.product.imageUrl,
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
                text = "x${item.quantity}",
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.product.name,
            style = MaterialTheme.typography.body1
        )
        Spacer(
            modifier = Modifier
                .weight(1f, true)
        )
        Text(
            text = NumberFormat.getCurrencyInstance().also {
                it.currency = Currency.getInstance(item.product.currencyCode)
            }.format(item.product.price),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
@Preview
fun CartItemViewPreview() {

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