package org.billsplit.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDetail(
    val id: Int,
    val sid: Int,
    val name: String,
    val code: String,
    val description: String,
    @SerialName("product_price") val productPrice: String,
    val type: String,
    @SerialName("age_restriction") val ageRestriction: String,
    @SerialName("fmb_sts") val fmbSts: Int,
    val void: Boolean
)
