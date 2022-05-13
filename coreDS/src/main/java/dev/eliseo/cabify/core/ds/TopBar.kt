package dev.eliseo.cabify.core.ds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String
) {
    TopAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        contentColor = MaterialTheme.colors.primary,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = title,
        )
    }
}