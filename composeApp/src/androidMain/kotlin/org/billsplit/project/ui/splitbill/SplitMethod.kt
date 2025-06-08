package org.billsplit.project.ui.splitbill

enum class SplitMethod(val displayName: String) {
    SPLIT_EVENLY("Split Evenly"),
    SPLIT_BY_ITEM("Split by Item"),
    SPLIT_BY_AMOUNTS("Split by Amounts"),
    SPLIT_BY_SHARES("Split by Shares"),
    SPLIT_BY_PERCENTAGE("Split by Percentage")
}