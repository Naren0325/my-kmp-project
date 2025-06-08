import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import org.billsplit.project.theme.responsive.WindowSize
import org.billsplit.project.theme.responsive.WindowType


@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberWindowSize(): WindowSize {
    val windowSizeClass = calculateWindowSizeClass()

    return WindowSize(
        width =
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> WindowType.SMALL
            WindowWidthSizeClass.Medium -> WindowType.MEDIUM
            WindowWidthSizeClass.Expanded -> WindowType.LARGE
            else -> WindowType.SMALL
        },
        height =
        when (windowSizeClass.heightSizeClass) {
            WindowHeightSizeClass.Compact -> WindowType.SMALL
            WindowHeightSizeClass.Medium -> WindowType.MEDIUM
            WindowHeightSizeClass.Expanded -> WindowType.LARGE
            else -> WindowType.SMALL
        }
    )
}
