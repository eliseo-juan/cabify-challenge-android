package dev.eliseo.cabify.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Label(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small)
            .padding(all = 4.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.caption
        )
    }
}