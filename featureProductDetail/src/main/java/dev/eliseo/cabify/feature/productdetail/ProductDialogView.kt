package dev.eliseo.cabify.feature.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.ds.Counter
import dev.eliseo.cabify.core.ds.Divider
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.core.presentation.view.DiscountContainerView
import dev.eliseo.cabify.domain.dto.ProductDetails
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.core.presentation.base.ViewModelScreen

@Composable
fun ProductDialogView(
    viewModel: ProductDialogViewModel,
) {
    ViewModelScreen(
        viewModel = viewModel,
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

    Surface(
        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large),
                    model = state.productDetails?.product?.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End
                ) {
                    PriceContainerView(product = state.productDetails?.product)
                    if (state.productDetails?.discount != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        DiscountContainerView(discount = state.productDetails.discount!!)
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = state.productDetails?.product?.name ?: "",
                style = MaterialTheme.typography.h5
            )

            Divider(Modifier.padding(8.dp))

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier,
                    text = state.productDetails?.quantity?.toString() ?: "-",
                    style = MaterialTheme.typography.h6
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f, true)
                )
                Counter(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 40.dp),
                    addIcon = Icons.Outlined.Add,
                    addText = "Add",
                    subIcon = Icons.Outlined.Remove,
                    onAddClick = {
                        events(ProductDialogViewModel.Event.AddProduct)
                    },
                    onSubClick = {
                        events(ProductDialogViewModel.Event.RemoveProduct)
                    },
                )
            }
        }
    }
}

@Composable
fun PriceContainerView(
    modifier: Modifier = Modifier,
    product: Product?,
) {
    Text(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        text = with(object : CurrencyRetriever {}) {
            product?.price?.getWhitCurrencyFormat(
                product.currencyCode
            ) ?: ""
        },
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h4
    )
}

@Preview(showBackground = false)
@Composable
fun ProductDialogViewRender() {
    ProductDialogView(
        state = ProductDialogViewModel.State(
            productDetails = ProductDetails(
                product = Product(
                    code = "TSHIRT",
                    name = "Cabify T-Shirt",
                    imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png",
                    price = 10.0,
                    currencyCode = "EUR",

                    ),
                discount = Discount.TakeXGetY(3, 1),
                quantity = 6
            )
        )
    ) {

    }
}