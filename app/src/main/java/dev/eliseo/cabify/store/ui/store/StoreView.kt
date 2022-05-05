package dev.eliseo.cabify.store.ui.store

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.eliseo.cabify.store.ui.AppDestinations

@Composable
fun StoreView(
    navController: NavController,
    viewModel: StoreViewModel = viewModel()
) {
    val state: StoreViewModel.State by viewModel.uiState.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(state.products.keys.toList()) { product ->
            StoreRow(
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            AppDestinations.ProductDialog.getNavigationLink(
                                product.code
                            )
                        )
                    },
                product = product,
                discount = state.products[product]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreViewPreview() {
    StoreView(rememberNavController())
}