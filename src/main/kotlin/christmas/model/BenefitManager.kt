package christmas.model

import christmas.model.constant.Category
import christmas.model.constant.Category.Companion.getChampagnePrice
import christmas.model.constant.DayOfWeek
import christmas.model.constant.SpecialDay

class BenefitManager {

    fun discountAll(visitDate: Int, orderedMenus: List<OrderedMenu>): List<Pair<String, Int>> {
        val discountInfoList = mutableListOf<Pair<String, Int>>()
        val discountContents = listOf<Pair<String, Int>>(
            discountWeekdaysOrWeekends(visitDate, orderedMenus),
            discountSpecialDays(visitDate),
            discountChristmasDay(visitDate),
            presentEvent(orderedMenus.sumOf { it.getAmount() }),
        )
        discountContents.forEach { applyDiscount(it, discountInfoList) }
        return discountInfoList.toList()
    }

    private fun applyDiscount(discount: Pair<String, Int>, discountInfoList: MutableList<Pair<String, Int>>) {
        if (discount.second != NOT_DISCOUNT_AMOUNT) discountInfoList.add(discount)
    }

    // 평일, 주말 확인 후 할인
    fun discountWeekdaysOrWeekends(visitDate: Int, orderedMenus: List<OrderedMenu>): Pair<String, Int> {
        return when ((visitDate - 1) % DayOfWeek.entries.size) {
            in DayOfWeek.SUNDAY.ordinal..DayOfWeek.THURSDAY.ordinal -> {
                WEEKDAYS to orderedMenus.filter { it.getCategory() == Category.DESSERT }.sumOf { it.quantity } * 2_023
            }

            in DayOfWeek.FRIDAY.ordinal..DayOfWeek.SATURDAY.ordinal -> {
                WEEKENDS to orderedMenus.filter { it.getCategory() == Category.MAIN }.sumOf { it.quantity } * 2_023
            }

            else -> NONE to NOT_DISCOUNT_AMOUNT
        }
    }

    fun discountSpecialDays(visitDate: Int): Pair<String, Int> {
        if (SpecialDay.entries.any { it.value == visitDate }) return SPECIAL to SPECIAL_DISCOUNT_AMOUNT
        return NONE to NOT_DISCOUNT_AMOUNT
    }

    fun discountChristmasDay(visitDate: Int): Pair<String, Int> {
        if (visitDate >= EVENT_END_DAY) return NONE to NOT_DISCOUNT_AMOUNT
        return CHRISTMAS_DAY to 1_000 + (visitDate - 1) * 100
    }

    private fun presentEvent(amount: Int): Pair<String, Int> {
        if (amount >= 120_000) {
            return PRESENT to getChampagnePrice()
        }

        return NONE to NOT_DISCOUNT_AMOUNT
    }

    companion object {
        const val WEEKDAYS = "평일 할인"
        const val WEEKENDS = "주말 할인"
        const val SPECIAL = "특별 할인"
        const val CHRISTMAS_DAY = "크리스마스 디데이 할인"
        const val PRESENT = "증정 이벤트"
        const val NONE = ""
        const val SPECIAL_DISCOUNT_AMOUNT = 1_000
        const val NOT_DISCOUNT_AMOUNT = 0
        const val EVENT_END_DAY = 26
    }
}
