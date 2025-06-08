package org.billsplit.project.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import rememberWindowSize


@Composable
fun AppTheme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val windowSize = rememberWindowSize()
    val typography = createTypography(windowSize)
    val shape = createShape(windowSize)

    val colors =
        if (useDarkTheme) {
            darkColorScheme(
                primary = Color(0XFF6591CC),
                onPrimary = Color(0XFF001633),
                primaryContainer = Color(0XFF000B1A),
                onPrimaryContainer = Color(0XFF98BEF2),
                secondary = Color(0XFFF3DBAD),
                onSecondary = Color(0XFF5A4214),
                secondaryContainer = Color(0XFF2D210A),
                onSecondaryContainer = Color(0XFFFCF6EA),
                tertiary = Color(0xFFB8C2D1),
                onTertiary = Color(0xFF1F2938),
                tertiaryContainer = Color(0xFF0F151C),
                onTertiaryContainer = Color(0xFFEDF0F3)
            )
        } else {
            lightColorScheme(
                primary = Color(0XFF003680),
                onPrimary = Color(0XFF003680),
                primaryContainer = Color(0XFF98BEF2),
                onPrimaryContainer = Color(0XFF001026),
                secondary = Color(0xFFE0A531),
                onSecondary = Color(0XFFE0A531),
                secondaryContainer = Color(0XFFFCF6EA),
                onSecondaryContainer = Color(0XFF43320F),
                background = Color(0xFFF2F6FA),
                surfaceVariant = Color(0xFFFFFFFF),
                surface = Color(0xFFFFFFFF),
                tertiary = Color(0xFF4D678B),
                onTertiary = Color(0xFF4D678B),
                tertiaryContainer = Color(0xFFEDF0F3),
                onTertiaryContainer = Color(0xFFEDF0F3),
                error = Color(0xFFE93835)
            )
        }
    MaterialTheme(
        typography = typography,
        content = content,
        colorScheme = colors,
        shapes = shape
    )
}

// Use with MaterialTheme.colors.dataGridHeaderColor
// Used for container, card, box background color
val ColorScheme.containerBackground: Color
    get() = Color(0xFFF3F6FF)

val ColorScheme.titleText: Color
    get() = Color(0XFF212121)

val ColorScheme.bodyText: Color
    get() = Color(0xFF3d3d3d)

val ColorScheme.subTitle: Color
    get() = Color(0xFF545454)

val ColorScheme.description: Color
    get() = Color(0xFF707070)

val ColorScheme.icon: Color
    get() = Color(0xFF808080)

val ColorScheme.labelText: Color
    get() = Color(0XFF9E9E9E)

val ColorScheme.secondaryDataGridHeader: Color
    get() = Color(0xFFF2F2F2)

val ColorScheme.header: Color
    get() = Color(0XFFCCCCCC)

val ColorScheme.buttonColor: Color
    get() = Color(0XFF6AA6FF)

val ColorScheme.borderColor: Color
    get() = Color(0XFF707070)

val ColorScheme.borderLightColor: Color
    get() = Color(0XFFCCCCCC)

val ColorScheme.borderShadow: Color
    get() = Color(0xFF466DA2)

val ColorScheme.ContainerShadow: Color
    get() = Color(0xFF144789)
