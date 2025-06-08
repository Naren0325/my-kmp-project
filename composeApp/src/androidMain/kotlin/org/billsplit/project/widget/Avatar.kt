package org.billsplit.project.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.billsplit.project.theme.bodyText
import org.billsplit.project.theme.borderLightColor
import org.billsplit.project.theme.description
import org.billsplit.project.theme.responsive.WindowType
import rememberWindowSize
import kotlin.random.Random

@Composable
fun Avatar(
    text: String,
    title: String,
    subTitle: String
) {
    // Get the current window size
    val windowSize = rememberWindowSize()

    // Adjust avatar size based on the window size
    val avatarSize = when (windowSize.width) {
        WindowType.SMALL -> 24.dp // Smaller avatar for mobile screens
        WindowType.MEDIUM -> 32.dp // Medium size for tablet
        WindowType.LARGE -> 32.dp // Larger size for desktop
    }
    val randomColor = generateRandomColor()
    Row(
        Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Always show the avatar box
        Box(
            modifier = Modifier
                .size(avatarSize) // Set the avatar size dynamically
                .background(MaterialTheme.colorScheme.borderLightColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.split(" ").joinToString("") { it.take(1) }.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White // Text color
            )
        }
        // Conditionally show title and subtitle for tablet and desktop
        if (windowSize.width != WindowType.SMALL) {
            Spacer(Modifier.width(10.dp)) // Spacer only for larger screens
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.bodyText
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.description
                )
            }
        }
    }
}

fun generateRandomColor(): Color {
    val rnd = Random.Default
    return Color(
        red = rnd.nextInt(256) / 255f,
        green = rnd.nextInt(256) / 255f,
        blue = rnd.nextInt(256) / 255f,
        alpha = 1f
    )
}

@Composable
fun AvatarWithoutSubTitle(
    text: String,
    title: String,
) {
    // Get the current window size
    val windowSize = rememberWindowSize()

    // Adjust avatar size based on the window size
    val avatarSize = when (windowSize.width) {
        WindowType.SMALL -> 24.dp // Smaller avatar for mobile screens
        WindowType.MEDIUM -> 32.dp // Medium size for tablet
        WindowType.LARGE -> 32.dp // Larger size for desktop
    }
    val randomColor = generateRandomColor()
    Row(
        Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Always show the avatar box
        Box(
            modifier = Modifier
                .size(avatarSize) // Set the avatar size dynamically
                .background(MaterialTheme.colorScheme.borderLightColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.split(" ").joinToString("") { it.take(1) }.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White // Text color
            )
        }
        // Conditionally show title and subtitle for tablet and desktop
        if (windowSize.width != WindowType.SMALL) {
            Spacer(Modifier.width(10.dp)) // Spacer only for larger screens
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.bodyText
                )
            }
        }
    }
}