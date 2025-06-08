package org.billsplit.project.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.billsplit.project.theme.responsive.WindowType
import rememberWindowSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SetupLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    content2: @Composable ColumnScope.() -> Unit
) {
    // Get the responsive size classes
    val windowSizeClass = calculateWindowSizeClass()

    // Box to handle responsiveness
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val contentWidth = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> maxWidth * 1f // Use 100% of screen width for compact
            WindowWidthSizeClass.Medium -> maxWidth * 1f // Use 100% of screen width for medium
            WindowWidthSizeClass.Expanded -> maxWidth * 0.5f // Use 50% of screen width for expanded
            else -> maxWidth * 0.8f // Fallback for unexpected cases
        }

        val spacing = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 8.dp // Smaller spacing for compact
            WindowWidthSizeClass.Medium -> 12.dp // Medium spacing for medium
            WindowWidthSizeClass.Expanded -> 26.dp // Larger spacing for expanded
            else -> 16.dp // Default spacing
        }
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
                Column(
                    modifier = Modifier.width(contentWidth),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    content()
                    Spacer(Modifier.height(spacing))
                    content2()
                }
            }

            WindowWidthSizeClass.Expanded -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .width(contentWidth)
                    ) {
                        content()
                    }
                    Spacer(Modifier.width(spacing))
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .width(contentWidth)
                    ) {
                        content2()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeLayout(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    // window responsive
    val windowSize = rememberWindowSize()
    val windowSizeClass = calculateWindowSizeClass()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val cardWidth = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> maxWidth * 0.9f // Use 90% of the screen width
            WindowWidthSizeClass.Medium -> maxWidth * 0.95f // Use 95% of the screen width
            WindowWidthSizeClass.Expanded -> maxWidth * 1f // Use 100% of the screen width
            else -> maxWidth * 0.9f
        }

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedCard(
                        Modifier
                            .width(cardWidth)
                            .padding(if (windowSize.width == WindowType.SMALL) 8.dp else 8.dp).width(cardWidth),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Box {
                            content()
                        }
                    }
                }
            }

            WindowWidthSizeClass.Expanded -> {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier =
                            Modifier.padding(26.dp).width(cardWidth),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Box {
                            content()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SetBodyLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    content2: @Composable ColumnScope.() -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val contentWidth = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> maxWidth * 1f // Use 100% of screen width for compact
            WindowWidthSizeClass.Medium -> maxWidth * 1f // Use 100% of screen width for medium
            WindowWidthSizeClass.Expanded -> maxWidth * 0.5f // Use 50% of screen width for expanded
            else -> maxWidth * 0.8f // Fallback for unexpected cases
        }

        val spacing = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 8.dp // Smaller spacing for compact
            WindowWidthSizeClass.Medium -> 12.dp // Medium spacing for medium
            WindowWidthSizeClass.Expanded -> 26.dp // Larger spacing for expanded
            else -> 16.dp // Default spacing
        }
        when (windowSizeClass.widthSizeClass) {
//            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
//                Column(
//                    modifier = Modifier.width(contentWidth),
//                    verticalArrangement = Arrangement.Top,
//                    horizontalAlignment = Alignment.Start
//                ) {
//                    content()
//                    Spacer(Modifier.height(spacing))
//                    content2()
//                }
//            }

            WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .width(contentWidth)
                    ) {
                        content()
                    }
                    Spacer(Modifier.width(spacing))
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .width(contentWidth)
                    ) {
                        content2()
                    }
                }
            }
        }
    }
}