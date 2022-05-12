package dev.eliseo.cabify.core.ds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    addIcon: ImageVector,
    addText: String,
    subIcon: ImageVector,
    onAddClick: () -> Unit,
    onSubClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Surface(
            modifier = Modifier
                .defaultMinSize(minWidth = 40.dp, minHeight = 40.dp)
                .clickable(onClick = onAddClick),
            shape = RoundedCornerShape(40.dp),
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ) {
            Row(
                modifier = modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(imageVector = addIcon, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = addText)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            modifier = Modifier
                .defaultMinSize(minWidth = 40.dp, minHeight = 40.dp)
                .clickable(onClick = onSubClick),
            shape = RoundedCornerShape(40.dp),
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ) {
            Row(
                modifier = modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = subIcon, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun CounterPreview() {
    Counter(
        addIcon = Icons.Outlined.Add,
        addText = "Add",
        subIcon = Icons.Outlined.Remove,
        onAddClick = {},
        onSubClick = {},
    )
}

