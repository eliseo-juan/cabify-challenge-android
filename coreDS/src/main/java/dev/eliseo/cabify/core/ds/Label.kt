package dev.eliseo.cabify.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Label(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    modifier = modifier
                        .size(20.dp)
                        .padding(end = 4.dp),
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary,
                )
            }
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@Composable
@Preview
fun LabelPreview() {
    Label(
        text = "Label",
        icon = Icons.Default.Person,
    )
}