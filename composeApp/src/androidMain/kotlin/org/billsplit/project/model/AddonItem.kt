package org.billsplit.project.model

import kotlinx.serialization.Serializable

@Serializable
data class AddonItem(
    val id: Int? = null,
    val name: String? = null,
    val price: String? = null,
    val qty: Int? = null
)