package org.billsplit.project.ui.tab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.billsplit.project.model.OrderWrapper
import org.billsplit.project.theme.borderLightColor
import org.billsplit.project.theme.labelText
import org.billsplit.project.ui.splitbill.SplitState
import org.billsplit.project.ui.viewmodel.BillSplitViewModel
import org.billsplit.project.widget.CheckBoxFiled
import org.billsplit.project.widget.SetBodyLayout
import org.billsplit.project.widget.SplitOrderLayout
import org.billsplit.project.widget.TextSpaceBetween
import org.jetbrains.compose.resources.stringResource
import splitapp.composeapp.generated.resources.Res
import splitapp.composeapp.generated.resources.pay
import splitapp.composeapp.generated.resources.person

@Composable
fun SplitByItemScreen(viewModel: BillSplitViewModel) {


    val state by viewModel.splitState.collectAsState()
    val orders = viewModel.orders.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.loadOrders(tableId = 86)
    }

    when {
        state == SplitState.LOADING -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
        }

        state == SplitState.FAILURE -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                if (state == SplitState.LOADING) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }

                errorMessage?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }

                LazyColumn {
                    items(orders) { order ->
                        SplitByItemsOrder(order)
                    }
                }
            }
        }
    }
}

@Composable
fun SplitByItemsOrder(orderWrapper: OrderWrapper) {
    var isExpanded by remember { mutableStateOf(false) }

    val order = orderWrapper.order

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        SplitOrderLayout(
            orderWrapper = orderWrapper,
            content = {
                SetBodyLayout(
                    content = {
                        CheckBoxFiled(
                            avatarName = "${stringResource(Res.string.person)} ${order.userId}",
                            name = "${stringResource(Res.string.person)} ${order.userId}",
                            onCheck = { /* Optional */ }
                        )
                    },
                    content2 = {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Box(Modifier.weight(0.7f)) {
                                Text(
                                    "£ 0.0",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W600,
                                    color = MaterialTheme.colorScheme.labelText,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                                )
                            }

                            Spacer(Modifier.width(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .border(
                                        0.3.dp,
                                        MaterialTheme.colorScheme.borderLightColor,
                                        MaterialTheme.shapes.small
                                    )
                                    .clickable {

                                    }
                            ) {
                                Text(
                                    stringResource(Res.string.pay),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W600,
                                    color = MaterialTheme.colorScheme.labelText,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                                )
                            }

                            Spacer(Modifier.width(8.dp))

                            Icon(
                                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.labelText,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        isExpanded = !isExpanded
                                    }
                            )
                        }
                    }
                )

                // Drop-down
                AnimatedVisibility(visible = isExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 36.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        val validItems = orderWrapper.item.filter {
                            it.qty >= 0 && it.total >= 0.0.toString() && it.itemName.isNotBlank()
                        }

                        if (validItems.isNotEmpty()) {
                            validItems.forEach { item ->
                                TextSpaceBetween(
                                    title = "${item.itemName} (${item.item.type})",
                                    text = "${item.qty}x £${item.total}"
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

