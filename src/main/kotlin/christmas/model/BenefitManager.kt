package christmas.model

import christmas.model.constant.Category
import christmas.model.constant.DayOfWeek
import christmas.model.constant.SpecialDay

class BenefitManager {

    fun discountAll(visitDate: Int, orderedMenus: List<OrderedMenu>): List<Pair<String, Int>> {
        val discountInfoList = mutableListOf<Pair<String,Int>>()
        val discountWeekdaysOrWeekends = discountWeekdaysOrWeekends(visitDate, orderedMenus)
        val discountSpecialDays = discountSpecialDays(visitDate)
        val discountChristmasDay = discountChristmasDay(visitDate)
        val presentEvent = presentEvent(orderedMenus.sumOf { it.getAmount() })
        if (discountWeekdaysOrWeekends.first != NONE) discountInfoList.add(discountWeekdaysOrWeekends)
        if (discountSpecialDays.first != NONE) discountInfoList.add(discountSpecialDays)
        if (discountChristmasDay.first != NONE) discountInfoList.add(discountChristmasDay)
        if (presentEvent.first != NONE) discountInfoList.add(presentEvent)
        return discountInfoList.toList()
    }

    // 평일, 주말 확인 후 할인
    fun discountWeekdaysOrWeekends(visitDate: Int, orderedMenus: List<OrderedMenu>): Pair<String, Int> {
        return when((visitDate-1)%DayOfWeek.entries.size) {
            in DayOfWeek.SUNDAY.ordinal..DayOfWeek.THURSDAY.ordinal -> {
                WEEKDAYS to orderedMenus.filter { it.getCategory() == Category.DESSERT }.sumOf { it.quantity }* 2_023
            }
            in DayOfWeek.FRIDAY.ordinal..DayOfWeek.SATURDAY.ordinal -> {
                WEEKENDS to orderedMenus.filter { it.getCategory() == Category.MAIN }.sumOf { it.quantity } * 2_023
            }
            else -> NONE to 0
        }
    }

    fun discountSpecialDays(visitDate: Int): Pair<String, Int> {
        if (SpecialDay.entries.any {it.value == visitDate}) return SPECIAL to SPECIAL_DISCOUNT_AMOUNT
        return NONE to 0
    }

    fun discountChristmasDay(visitDate: Int): Pair<String, Int> {
        if (visitDate >= 26) return NONE to 0
        return CHRISTMAS_DAY to 1_000 + (visitDate-1) * 100
    }

    fun presentEvent(amount: Int): Pair<String, Int> {
        if (amount >= 120_000) {
            return PRESENT to 25000
        }
        return NONE to 0
    }

    companion object {
        const val WEEKDAYS = "평일 할인"
        const val WEEKENDS = "주말 할인"
        const val SPECIAL = "특별 할인"
        const val CHRISTMAS_DAY = "크리스마스 디데이 할인"
        const val PRESENT = "증정 이벤트"
        const val NONE = ""
        const val SPECIAL_DISCOUNT_AMOUNT = 1_000
    }
}