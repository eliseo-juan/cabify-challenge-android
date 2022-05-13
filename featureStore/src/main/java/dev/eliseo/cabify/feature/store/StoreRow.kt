package dev.eliseo.cabify.feature.store

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.ds.Label
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product

@Composable
fun StoreRow(
    modifier: Modifier = Modifier,
    product: Product,
    discount: Discount? = null,
    onClick: (() -> Unit)?
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    enabled = onClick != null,
                    onClick = { onClick?.invoke() }
                )
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxHeight()
                    .aspectRatio(1f),
                model = product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = with(object : CurrencyRetriever {}) {
                            product.price.getWhitCurrencyFormat(product.currencyCode)
                        },
                        style = MaterialTheme.typography.h6
                    )
                    if (discount != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Label(
                            text = "Promo",
                            icon = Icons.Default.Star
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun StoreRowPreview() {
    StoreRow(
        product = Product(
            "VOUCHER",
            "Cabify Voucher",
            5.0,
            currencyCode = "EUR",
            imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
        ),
        discount = Discount.TakeXGetY(2, 1),
    ) {}
}