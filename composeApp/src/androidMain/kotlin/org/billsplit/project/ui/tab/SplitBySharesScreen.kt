package org.billsplit.project.ui.tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.billsplit.project.model.OrderWrapper
import org.billsplit.project.theme.labelText
import org.billsplit.project.ui.splitbill.SplitState
import org.billsplit.project.ui.viewmodel.BillSplitViewModel
import org.billsplit.project.widget.*
import org.jetbrains.compose.resources.stringResource
import splitapp.composeapp.generated.resources.*
import splitapp.composeapp.generated.resources.Res
import splitapp.composeapp.generated.resources.paid

@Composable
fun SplitBySharesScreen(viewModel: BillSplitViewModel) {

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
                        SplitBySharesOrder(order)
                    }
                }
            }
        }
    }
}

@Composable
fun SplitBySharesOrder(orderWrapper: OrderWrapper) {
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        val order = orderWrapper.order
        SplitOrderLayout(
            orderWrapper = orderWrapper,
            content = {
                SetBodyLayout(
                    content = {
                        CheckBoxFiled(
                            avatarName = "${stringResource(Res.string.person)} ${order.userId}",
                            name = "${stringResource(Res.string.person)} ${order.userId}",
                            onCheck = { checked ->
                                isChecked = checked
                                Logger.d("Checkbox for ${order.userId} is $checked")
                            }
                        )
                    },
                    content2 = {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Quantity(
                                quantity = "1",
                                addButton = {},
                                removeButton = {}

                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                stringResource(Res.string.paid),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.W600,
                                color = MaterialTheme.colorScheme.labelText
                            )
                        }
                    }
                )
            },
        )
    }
}