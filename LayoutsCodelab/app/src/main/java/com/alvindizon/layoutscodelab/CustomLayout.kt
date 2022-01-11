package com.alvindizon.layoutscodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alvindizon.layoutscodelab.ui.theme.LayoutsCodelabTheme

fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun FirstBaselineToTopVsNormalPaddingPreview() {
    LayoutsCodelabTheme {
        Column {
            Row {
                Text("Hi there FirstBaselineToTop!", Modifier.firstBaselineToTop(32.dp))
            }
            Row {
                Text("Hi there normal padding", Modifier.padding(top = 32.dp))
            }
        }
    }
}


@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // set the size of the layout as big as possible
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                // position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // record new y-coordinate
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun CustomLayout(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically")
        Text("We've done it by hand!")
    }
}

@Preview
@Composable
fun CustomLayoutPreview() {
    CustomLayout()
}
