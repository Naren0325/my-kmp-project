package org.billsplit.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(
    val id: Int,
    @SerialName("order_id") val orderId: Int,
    val itemid: Int,
    @SerialName("item_name") val itemName: String,
    val qty: Int,
    val price: String,
    val discount: String,
    @SerialName("discount_amount") val discountAmount: Double,
    val final_item_amount: Double,
    @SerialName("discount_type") val discountType: String,
    @SerialName("discount_value") val discountValue: Int,
    val total: String,
    val item: ItemDetail,
    val addon: List<AddonItem> = emptyList(),
    val finalTotal: Double
)
