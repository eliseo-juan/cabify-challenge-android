package dev.eliseo.cabify.store.ui.product_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import dev.eliseo.cabify.store.ui.ds.Counter
import dev.eliseo.cabify.store.ui.ds.Label
import dev.eliseo.cabify.store.ui.store.DiscountTitleStringRetriever

@Composable
fun ProductDialogView(
    navController: NavController,
    viewModel: ProductDialogViewModel = viewModel()
) {
    val state: ProductDialogViewModel.State by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = state.product?.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        state.discount?.let {
            Label(
                text = with(object : DiscountTitleStringRetriever {}) {
                    it.getTitle(LocalContext.current)
                },
            )
        }

        Text(
            text = state.product?.name ?: "",
            style = MaterialTheme.typography.h3
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = state.product?.name ?: "",
                style = MaterialTheme.typography.h2
            )
            Counter(
                counter = remember { mutableStateOf(state.cartCount) },
            )
        }

        Text(
            text = state.product?.description ?: "",
            style = MaterialTheme.typography.body1
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProductDialogViewRender() {
    ProductDialogView(
        navController = rememberNavController(),
    )
}