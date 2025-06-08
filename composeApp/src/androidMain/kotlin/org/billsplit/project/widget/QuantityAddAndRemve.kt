package org.billsplit.project.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.billsplit.project.theme.bodyText
import org.billsplit.project.theme.borderLightColor

@Composable
fun Quantity(
    quantity: String,
    removeButton: () -> Unit,
    addButton: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .border(0.5.dp, color = MaterialTheme.colorScheme.borderLightColor, shape = MaterialTheme.shapes.extraSmall)
            .background(Color.White)
    ) {
        IconButton(
            modifier = Modifier.size(34.dp),
            onClick = removeButton
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "date backward",
                modifier = Modifier.size(12.dp),
                tint = MaterialTheme.colorScheme.bodyText
            )
        }
        VerticalDivider(
            color = MaterialTheme.colorScheme.borderLightColor,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxHeight()
        )
        Text(
            text = quantity,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
            color = MaterialTheme.colorScheme.bodyText,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        VerticalDivider(
            color = MaterialTheme.colorScheme.borderLightColor,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxHeight()
        )
        IconButton(
            modifier = Modifier.size(34.dp),
            onClick = addButton
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "date forward",
                modifier = Modifier.size(12.dp),
                tint = MaterialTheme.colorScheme.bodyText
            )
        }
    }
}