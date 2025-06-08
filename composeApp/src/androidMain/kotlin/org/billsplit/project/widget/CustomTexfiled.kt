package org.billsplit.project.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    placeholder: String = "",
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: String? = null,
    color: Color? = null,
    leadingIcon: @Composable (() -> Unit)? = null, // Leading icon
    trailingIcon: @Composable (() -> Unit)? = null, // Trailing icon
    focusRequester: FocusRequester = FocusRequester(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onNext: () -> Unit = {},
    onTabPress: () -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    val cardBackgroundColor = color ?: MaterialTheme.colorScheme.surface // Fallback color if no custom color provided

    val windowSizeClass = calculateWindowSizeClass()

    val minWidth = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 200.dp
        WindowWidthSizeClass.Medium -> 250.dp
        WindowWidthSizeClass.Expanded -> 300.dp
        else -> 200.dp
    }
    val horizontalPadding = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 12.dp
        WindowWidthSizeClass.Medium -> 16.dp
        WindowWidthSizeClass.Expanded -> 20.dp
        else -> 12.dp
    }

    val fontSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> MaterialTheme.typography.bodySmall.fontSize
        WindowWidthSizeClass.Medium -> MaterialTheme.typography.bodyMedium.fontSize
        WindowWidthSizeClass.Expanded -> MaterialTheme.typography.bodySmall.fontSize
        else -> MaterialTheme.typography.bodySmall.fontSize
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        BoxWithConstraints {
            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    containerColor = cardBackgroundColor
                ),
                modifier = Modifier.widthIn(min = minWidth),
                border = BorderStroke(
                    0.5.dp,
                    if (isError) {
                        MaterialTheme.colorScheme.error
                    } else if (isFocused) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.LightGray
                    }
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                ) {
                    // Leading Icon
                    if (leadingIcon != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            leadingIcon()
                        }
                    }

                    // Text Field
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        readOnly = readOnly,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            }
                            .onKeyEvent {
                                if (it.type == KeyEventType.KeyDown && it.key == Key.Tab) {
                                    onTabPress()
                                    onNext()
                                    true
                                } else {
                                    false
                                }
                            }
                            .padding(horizontal = horizontalPadding, vertical = 12.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        visualTransformation = visualTransformation,
                        singleLine = true,
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (value.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        overflow = TextOverflow.Ellipsis,
                                        minLines = 1,
                                        maxLines = 1,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Gray,
                                            fontSize = fontSize
                                        ),
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    // Trailing Icon
                    if (trailingIcon != null) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .size(24.dp) // Icon size
                        ) {
                            trailingIcon()
                        }
                    }
                }
            }
        }

        // Error Message
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}