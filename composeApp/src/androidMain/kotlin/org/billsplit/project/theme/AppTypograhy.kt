package org.billsplit.project.theme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.billsplit.project.theme.responsive.WindowSize
import org.billsplit.project.theme.responsive.WindowType
import org.jetbrains.compose.resources.Font
import splitapp.composeapp.generated.resources.Res
import splitapp.composeapp.generated.resources.Roboto_Regular

@Composable
fun createTypography(windowSize: WindowSize): Typography {
    val fontAwesome = FontFamily(Font(Res.font.Roboto_Regular))

    return Typography(
        displayLarge =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Light,
            fontSize = responsiveFontSize(windowSize, 45f, 57f, 64f).sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Light,
            fontSize = responsiveFontSize(windowSize, 36f, 45f, 52f).sp,
            letterSpacing = 0.sp
        ),
        displaySmall =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 28f, 36f, 44f).sp,
            letterSpacing = 0.sp
        ),
        headlineLarge =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 24f, 28f, 32f).sp,
            letterSpacing = 0.sp
        ),
        headlineMedium =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 20f, 24f, 26f).sp,
            letterSpacing = 0.25.sp
        ),
        headlineSmall =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 18f, 20f, 24f).sp,
            letterSpacing = 0.sp
        ),
        titleLarge =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Bold,
            fontSize = responsiveFontSize(windowSize, 18f, 20f, 24f).sp,
            lineHeight = responsiveFontSize(windowSize, 26f, 28f, 30f).sp,
            letterSpacing = 0.9.sp
        ),
        titleMedium =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Medium,
            fontSize = responsiveFontSize(windowSize, 16f, 16f, 18f).sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Medium,
            fontSize = responsiveFontSize(windowSize, 14f, 14f, 16f).sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 16f, 16f, 18f).sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.W400,
            fontSize = responsiveFontSize(windowSize, 14f, 14f, 14f).sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Normal,
            fontSize = responsiveFontSize(windowSize, 12f, 12f, 14f).sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Medium,
            fontSize = responsiveFontSize(windowSize, 14f, 14f, 16f).sp,
            letterSpacing = 1.25.sp,
            lineHeight = 20.sp
        ),
        labelMedium =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Medium,
            fontSize = responsiveFontSize(windowSize, 12f, 12f, 14f).sp,
            letterSpacing = 0.4.sp
        ),
        labelSmall =
        TextStyle(
            fontFamily = fontAwesome,
            fontWeight = FontWeight.Medium,
            fontSize = responsiveFontSize(windowSize, 10f, 10f, 12f).sp,
            letterSpacing = 1.5.sp
        )
    )
}

fun responsiveFontSize(
    windowSize: WindowSize,
    sm: Float,
    md: Float,
    lg: Float
): Float =
    when (windowSize.width) {
        WindowType.SMALL -> sm
        WindowType.MEDIUM -> md
        else -> lg
    }
