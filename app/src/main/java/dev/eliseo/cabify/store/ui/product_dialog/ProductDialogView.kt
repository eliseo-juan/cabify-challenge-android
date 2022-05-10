package dev.eliseo.cabify.store.ui.product_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.ds.Counter
import dev.eliseo.cabify.core.ds.Label
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.feature.store.DiscountTitleStringRetriever
import dev.eliseo.cabify.store.libbase.ViewModelScreen

@Composable
fun ProductDialogView(
    viewModel: ProductDialogViewModel,
    productId: String
) {
    ViewModelScreen(
        viewModel = viewModel,
        onInitialized = {
            viewModel.setEvent(ProductDialogViewModel.Event.OnLoaded(productId))
        },
        content = { state ->
            ProductDialogView(state) { event ->
                viewModel.setEvent(event)
            }
        }
    )
}

@Composable
private fun ProductDialogView(
    state: ProductDialogViewModel.State,
    events: (event: ProductDialogViewModel.Event) -> Unit
) {
    Column(
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colors.onPrimary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
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

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.product?.name ?: "",
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = state.product?.description ?: "",
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .height(56.dp),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colors.surface
        ) {
            Counter()
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ProductDialogViewRender() {
    ProductDialogView(state = ProductDialogViewModel.State(
        product = Product(
            code ="TSHIRT",
            name = "Cabify T-Shirt",
            description = "A T-shirt, or tee shirt, is a style of fabric shirt named after the T shape of its body and sleeves. Traditionally, it has short sleeves and a round neckline, known as a crew neck, which lacks a collar. T-shirts are generally made of a stretchy, light, and inexpensive fabric and are easy to clean. The T-shirt evolved from undergarments used in the 19th century and, in the mid-20th century, transitioned from undergarment to general-use casual clothing.",
            imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png",
            price = 10.0,
            currencyCode = "EUR",

        ),
    )) {

    }
}