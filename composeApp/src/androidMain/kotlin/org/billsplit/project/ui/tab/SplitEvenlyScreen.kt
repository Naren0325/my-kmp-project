package org.billsplit.project.ui.tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.billsplit.project.model.OrderWrapper
import org.billsplit.project.ui.splitbill.SplitState
import org.billsplit.project.ui.viewmodel.BillSplitViewModel
import org.billsplit.project.widget.CheckBoxFiled
import org.billsplit.project.widget.SplitOrderLayout

@Composable
fun SplitEvenlyScreen(viewModel: BillSplitViewModel) {

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
                        SplitEvenlyOrder(order)
                    }
                }
            }
        }
    }
}


@Composable
fun SplitEvenlyOrder(orderWrapper: OrderWrapper) {
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        val order = orderWrapper.order
        SplitOrderLayout(
            content = {
                CheckBoxFiled(
                    avatarName = "Person ${order.userId}",
                    name = "Person ${order.userId}",
                    onCheck = { checked ->
                        isChecked = checked
                        println("Checkbox for ${order.userId} is $checked")
                    }
                )
            },
            orderWrapper = orderWrapper
        )
    }
}

