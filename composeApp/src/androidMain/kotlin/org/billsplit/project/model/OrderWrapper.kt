package org.billsplit.project.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderWrapper(
    val order: Order,
    val item: List<OrderItem> = emptyList(),
    val void: List<VoidItem> = emptyList(),
    val qrCodeUrl: String? = null
)
