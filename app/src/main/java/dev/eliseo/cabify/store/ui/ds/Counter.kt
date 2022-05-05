package dev.eliseo.cabify.store.ui.ds

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counter: MutableState<Int> = remember{ mutableStateOf(0) },
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .border(1.dp, color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small),
        verticalAlignment = Alignment.CenterVertically

    ) {
        CounterButton(
            title = "-",
            onClick = { counter.value-- }
        )
        Text(
            modifier = Modifier
                .requiredWidth(40.dp),
            text = counter.value.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        CounterButton(
            title = "+",
            onClick = { counter.value++ }
        )
    }
}

@Composable
fun CounterButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.aspectRatio(1f),
        content = { Text(title) },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp,
            disabledElevation = 0.dp
        ),
        shape = RoundedCornerShape(0.dp),
        onClick = onClick
    )

}

@Preview
@Composable
fun CounterPreview() {
    Counter()
}

