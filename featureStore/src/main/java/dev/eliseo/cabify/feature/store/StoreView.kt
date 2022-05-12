package dev.eliseo.cabify.feature.store

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.eliseo.cabify.core.ds.SheetIndicator
import dev.eliseo.cabify.feature.store.cart.CartView
import dev.eliseo.cabify.store.libbase.ViewModelScreen

@Composable
fun StoreView(
    viewModel: StoreViewModel
) {
    ViewModelScreen(
        viewModel = viewModel
    ) { state ->
        StoreView(state) { event ->
            viewModel.setEvent(event)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
private fun StoreView(
    state: StoreViewModel.State,
    events: (event: StoreViewModel.Event) -> Unit
) {
    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                contentPadding = PaddingValues(0.dp),
                contentColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Cabify Store",
                )
            }
        },
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Expanded),
        ),
        sheetContent = {
            CartView(viewModel = hiltViewModel())
        },
        sheetShape = MaterialTheme.shapes.large.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        ),
        sheetPeekHeight = 64.dp,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text("Ir al pago")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = null
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                onClick = {
                    events(StoreViewModel.Event.OnGoToCheckout)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 200.dp),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.products.keys.toList()) { product ->
                StoreRow(
                    modifier = Modifier,
                    product = product,
                    discount = state.products[product],
                    onClick = {
                        events(StoreViewModel.Event.ProductClicked(product))
                    }
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun StoreViewPreview() {
    StoreView(
        state = StoreViewModel.State()
    ) {

    }
}