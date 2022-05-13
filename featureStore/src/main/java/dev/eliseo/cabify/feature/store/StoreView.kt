package dev.eliseo.cabify.feature.store

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.eliseo.cabify.core.ds.TopBar
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

    val bottomSheetState = rememberBottomSheetState(
        if (state.products.isNotEmpty()) {
            BottomSheetValue.Expanded
        } else {
            BottomSheetValue.Collapsed
        }
    )
    BottomSheetScaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.store_title))
        },
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = bottomSheetState,
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
                    Text(text = stringResource(id = R.string.store_cta_go_to_checkout))
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