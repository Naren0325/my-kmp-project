package org.billsplit.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoidItem(
    val id: Int,
    val oid: Int,
    val itemid: Int,
    @SerialName("item_name") val itemName: String,
    val price: Double,
    val qty: Int,
    val reason: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String
)
