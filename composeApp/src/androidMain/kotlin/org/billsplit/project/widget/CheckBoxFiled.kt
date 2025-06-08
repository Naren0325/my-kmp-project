package org.billsplit.project.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.billsplit.project.theme.titleText

@Composable
fun CheckBoxFiled(
    avatarName: String,
    name: String,
//    subText: String,
    onCheck: (Boolean) -> Unit,
    initialChecked: Boolean = false // Add this parameter
) {
    var checkBox by remember { mutableStateOf(initialChecked) } // Use the initialChecked value

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        Checkbox(
            modifier = Modifier.size(16.dp),
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.titleText,
                uncheckedColor = MaterialTheme.colorScheme.titleText,
                checkmarkColor = MaterialTheme.colorScheme.background
            ),
            checked = checkBox,
            onCheckedChange = { newRememberMe ->
                checkBox = newRememberMe
                onCheck(newRememberMe)
            }
        )
        Spacer(modifier = Modifier.width(7.dp))
        AvatarWithoutSubTitle(
            avatarName,
            name,
        )
    }
}
