package org.billsplit.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Int,
    @SerialName("user_id") val userId: Int,
    val sno: Int,
    @SerialName("sub_total") val subTotal: String,
    val discount: String ? = null,
    @SerialName("total_tax")val totalTax: String ? = null,
    @SerialName("service_charge")val serviceCharge: String ? = null,
    val total: String,
    val status: Int
)