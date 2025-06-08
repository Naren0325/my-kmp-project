package org.billsplit.project.model

import kotlinx.serialization.Serializable

@Serializable
data class GetOrderRequest(val apiKey: String, val tableId: Int)
