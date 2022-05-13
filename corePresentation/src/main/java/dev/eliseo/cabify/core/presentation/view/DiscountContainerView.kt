package dev.eliseo.cabify.core.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.eliseo.cabify.core.presentation.retriever.DiscountTitleStringRetriever
import dev.eliseo.cabify.domain.model.Discount

@Composable
fun DiscountContainerView(
    modifier: Modifier = Modifier,
    discount: Discount
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.primary)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp),
            imageVector = Icons.Outlined.Star,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            modifier = Modifier,
            text = with(object : DiscountTitleStringRetriever {}) {
                discount.getTitle(LocalContext.current)
            },
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body2
        )
    }
}