package org.billsplit.project.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.billsplit.project.model.OrderWrapper
import org.jetbrains.compose.resources.stringResource
import splitapp.composeapp.generated.resources.*
import splitapp.composeapp.generated.resources.Res
import splitapp.composeapp.generated.resources.discount
import splitapp.composeapp.generated.resources.items_total
import splitapp.composeapp.generated.resources.tax

@Composable
fun SplitOrderLayout(
    orderWrapper: OrderWrapper,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        val order = orderWrapper.order

        SetupLayout(
            content = {
                content()
            },
            content2 = {
                HeaderTextSpaceBetween(
                    title = stringResource(Res.string.items_total),
                    text = "$${order.total}"
                )

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

                HorizontalDivider()

                HeaderTextSpaceBetween(
                    title = "Avoid",
                    text = ""
                )

                if (orderWrapper.void.isNotEmpty()) {
                    orderWrapper.void.forEach { voidItem ->
                        TextSpaceBetween(
                            title = voidItem.itemName,
                            text = "${voidItem.qty}x $${voidItem.price}"
                        )
                    }
                } else {
                    Text("No voided items.")
                }

                HorizontalDivider()
                TextSpaceBetween(
                    title = stringResource(Res.string.service_charge),
                    text = order.serviceCharge ?: "£0.00"
                )

                TextSpaceBetween(
                    title = stringResource(Res.string.discount),
                    text = "£${order.discount ?: "£0.00"}"
                )

                TextSpaceBetween(
                    title = stringResource(Res.string.tax),
                    text = "£${order.totalTax ?: "£0.00"}"
                )

            }
        )
    }
}
