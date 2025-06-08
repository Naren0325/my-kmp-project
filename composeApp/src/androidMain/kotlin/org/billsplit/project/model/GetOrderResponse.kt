package org.billsplit.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetOrderResponse(
    val status: Boolean? = null,
    val message: List<OrderWrapper>? = null,
    @SerialName("response_code")
    val responseCode: Int? = null
)
