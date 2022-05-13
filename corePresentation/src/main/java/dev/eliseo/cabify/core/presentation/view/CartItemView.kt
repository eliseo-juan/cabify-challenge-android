package dev.eliseo.cabify.core.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.eliseo.cabify.core.presentation.retriever.CurrencyRetriever
import dev.eliseo.cabify.domain.dto.CartItem

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
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {

            item.withoutDiscountPrice?.let {
                Text(
                    text = with(object : CurrencyRetriever {}) {
                        it.getWhitCurrencyFormat(item.product.currencyCode)
                    },
                    textDecoration = TextDecoration.LineThrough,
                    style = MaterialTheme.typography.caption
                )
            }
            Text(
                text = with(object : CurrencyRetriever {}) {
                    item.finalPrice.getWhitCurrencyFormat(item.product.currencyCode)
                },
                style = MaterialTheme.typography.body1
            )
        }

    }
}

@Composable
@Preview
fun CartItemViewPreview() {

}