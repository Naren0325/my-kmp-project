package org.billsplit.project.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.billsplit.project.theme.responsive.WindowSize
import org.billsplit.project.theme.responsive.WindowType


@Composable
fun createShape(windowSize: WindowSize): Shapes {
    val windowShape =
        when (windowSize.width) {
            WindowType.SMALL -> 0.75f
            WindowType.MEDIUM -> 1.0f
            WindowType.LARGE -> 1.25f
        }
    return Shapes(
        extraSmall = RoundedCornerShape((4.dp * windowShape).value.dp),
        small = RoundedCornerShape((6.dp * windowShape).value.dp),
        medium = RoundedCornerShape((12.dp * windowShape).value.dp),
        large = RoundedCornerShape((24.dp * windowShape).value.dp),
        extraLarge = RoundedCornerShape((32.dp * windowShape).value.dp)
    )
}
