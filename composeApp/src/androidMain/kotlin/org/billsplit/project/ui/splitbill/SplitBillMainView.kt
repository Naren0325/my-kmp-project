package org.billsplit.project.ui.splitbill

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.billsplit.project.model.OrderWrapper
import org.billsplit.project.theme.*
import org.billsplit.project.ui.tab.*
import org.billsplit.project.ui.viewmodel.BillSplitViewModel
import org.billsplit.project.widget.HomeLayout
import org.jetbrains.compose.resources.stringResource
import splitapp.composeapp.generated.resources.Res
import splitapp.composeapp.generated.resources.split_bill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitBillMainView(viewModel: BillSplitViewModel) {

    val state by viewModel.splitState.collectAsState()

    val orders = viewModel.orders
    val errorMessage = viewModel.errorMessage
    val selectedTab = remember { mutableStateOf(SplitMethod.SPLIT_EVENLY) }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        HomeLayout(
            content = {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(Res.string.split_bill),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.titleText
                                )

                                IconButton(
                                    onClick = {
                                    },
//                                    modifier = Modifier.height(30.dp).width(30.dp)
                                ) {
                                    Icon(
                                        Icons.Outlined.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp, 15.dp),
                                        tint = MaterialTheme.colorScheme.borderColor
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    )
                    TabRow(
                        selectedTabIndex = selectedTab.value.ordinal,
                        contentColor = Color.Black,
                        indicator = { tabPositions ->
                            SecondaryIndicator(
                                Modifier
                                    .tabIndicatorOffset(tabPositions[selectedTab.value.ordinal])
                                    .height(2.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        SplitMethod.entries.forEachIndexed { index, method ->
                            Tab(
                                selected = selectedTab.value.ordinal == index,
                                onClick = { selectedTab.value = method },
                                text = {
                                    Text(
                                        text = method.displayName,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.W500,
                                        color = if (selectedTab.value.ordinal == index)
                                            MaterialTheme.colorScheme.primary else
                                            MaterialTheme.colorScheme.labelText
                                    )
                                }
                            )
                        }
                    }

                    // ----- navigation

                    when (selectedTab.value) {
                        SplitMethod.SPLIT_EVENLY -> SplitEvenlyScreen(viewModel)
                        SplitMethod.SPLIT_BY_ITEM -> SplitByItemScreen(viewModel)
                        SplitMethod.SPLIT_BY_AMOUNTS -> SplitByAmountScreen(viewModel)
                        SplitMethod.SPLIT_BY_SHARES -> SplitBySharesScreen(viewModel)
                        SplitMethod.SPLIT_BY_PERCENTAGE -> SplitByPercentageScreen(viewModel)
                    }
                }
            }
        )
    }
//    LaunchedEffect(Unit) {
//        viewModel.loadOrders(tableId = 89)
//    }
//
//    when {
//        isLoading -> {
//            CircularProgressIndicator()
//        }
//
//        errorMessage != null -> {
//            Text("Error: $errorMessage")
//        }
//
//        else -> {
//            LazyColumn {
//                items(orders.value) { order ->
//                    OrderCard(order)
//                }
//            }
//        }
//    }
}

@Composable
fun OrderCard(orderWrapper: OrderWrapper) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val order = orderWrapper.order
            Text("Order ID: ${order.id}")
            Text("User ID: ${order.userId}")
            Text("Total: \$${order.total}")
            Text("Sub Total: ${order.subTotal}")
            Text("Discount: ${order.discount}")
            Text("SNO: ${order.sno}")

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text("Order Items:", style = MaterialTheme.typography.titleMedium)

            orderWrapper.item.forEach { item ->
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text("• ${item.itemName} x${item.qty} - \$${item.total}")
                    Text("  Type: ${item.item.type}, Price: ${item.price}")
                    if (item.addon.isNotEmpty()) {
                        Text("  Addons:")
                        item.addon.forEach { addon ->
                            Text("    - ${addon.name} x${addon.qty} (\$${addon.price})")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text("Voided Items:", style = MaterialTheme.typography.titleMedium)

            if (orderWrapper.void.isNotEmpty()) {
                orderWrapper.void.forEach { voidItem ->
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text("• ${voidItem.itemName} x${voidItem.qty} - \$${voidItem.price}")
                        Text("  Reason: ${voidItem.reason}")
                    }
                }
            } else {
                Text("No voided items.")
            }
        }
    }
}

