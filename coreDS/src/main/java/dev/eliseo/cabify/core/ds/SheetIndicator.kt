package dev.eliseo.cabify.core.ds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SheetIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(4.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = Color.Black.copy(alpha = 0.2f))
        )
    }
}

@Composable
@Preview
fun SheetIndicatorPreview() {
    SheetIndicator(
        modifier = Modifier.height(16.dp)
    )
}