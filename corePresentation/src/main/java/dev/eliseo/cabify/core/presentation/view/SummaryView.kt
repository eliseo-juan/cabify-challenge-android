package dev.eliseo.cabify.core.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.eliseo.cabify.core.presentation.R

@Composable
fun SummaryView(
    modifier: Modifier = Modifier,
    price: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.generic_total),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = price,
            style = MaterialTheme.typography.h6
        )
    }
}