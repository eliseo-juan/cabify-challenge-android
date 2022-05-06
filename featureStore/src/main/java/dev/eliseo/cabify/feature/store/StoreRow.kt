package dev.eliseo.cabify.feature.store

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.domain.model.Discount
import dev.eliseo.cabify.domain.model.Product
import dev.eliseo.cabify.store.ui.ds.Counter
import dev.eliseo.cabify.store.ui.ds.Label
import java.text.NumberFormat
import java.util.*

@Composable
fun StoreRow(
    modifier: Modifier = Modifier,
    product: Product,
    discount: Discount? = null
) {
    Surface() {
        Row(
            modifier = modifier
                .height(128.dp)
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.large),
                model = product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = NumberFormat.getCurrencyInstance().also {
                            it.currency = Currency.getInstance(product.currencyCode)
                        }.format(product.price),
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (discount != null) {
                        Label(
                            text = with(object : DiscountTitleStringRetriever {}) {
                                discount.getTitle(LocalContext.current)
                            },
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f, true)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Counter()
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
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            5.0,
            currencyCode = "EUR",
            imageUrl = "https://brandemia.org/sites/default/files/inline/images/cabify_logo_nuevo_2.png"
        ),
        discount = Discount.TakeXGetY(2, 1),
    )
}