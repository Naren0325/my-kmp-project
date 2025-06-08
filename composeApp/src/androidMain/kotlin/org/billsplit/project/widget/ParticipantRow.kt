package org.billsplit.project.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//@Composable
//fun ParticipantRow(participant: Participant, onToggle: () -> Unit, onAmountChange: (Double) -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.padding(8.dp).fillMaxWidth()
//    ) {
//        CheckBoxFiled(
//            avatarName = participant.name.take(2).uppercase(),
//            name = participant.name,
//            subText = "£${"%.2f".format(participant.amount)}",
//            initialChecked = participant.included,
//            onCheck = { onToggle() }
//        )
//    }
//}