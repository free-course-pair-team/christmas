package christmas.model

data class BenefitsDetail(
    val dayDiscount: Int,
    val menuDiscount: Int?,
    val specialDiscount: Boolean = false,
    val presentEvent: Boolean = false
)
