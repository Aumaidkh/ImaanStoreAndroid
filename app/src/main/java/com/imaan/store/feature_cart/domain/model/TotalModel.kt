package com.imaan.store.feature_cart.domain.model

data class TotalModel(
    val type: TotalType = TotalType.SUB_TOTAL,
    val label: String,
    private val amount: Double
){
    val amountString get() =
        "$${amount}"

}

enum class TotalType {
    SUB_TOTAL,
    GRAND_TOTAL
}

val dummyTotals = listOf(
    TotalModel(
        label = "Delivery Charges",
        amount = 30.0
    ),
    TotalModel(
        label = "Subtotal",
        amount = 130.0
    ),
    TotalModel(
        label = "GST",
        amount = 10.0
    ),
    TotalModel(
        label = "Grand Total",
        amount =170.0
    ),
)
