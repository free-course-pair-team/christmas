package christmas.model

import christmas.model.constant.Category
import christmas.model.constant.DayOfWeek

class BenefitManager {

    // 평일, 주말 확인 후 할인
    fun discountWeekdaysOrWeekends(visitDate: Int, startDayOfWeek: DayOfWeek, orderedMenus: List<OrderedMenu>): Int {
        return when((visitDate+4) % DayOfWeek.entries.size) {
            in DayOfWeek.SUNDAY.ordinal..DayOfWeek.THURSDAY.ordinal -> {
                orderedMenus.count { it.getCategory() == Category.DESSERT } * 2_023
            }
            in DayOfWeek.FRIDAY.ordinal..DayOfWeek.SATURDAY.ordinal -> {
                orderedMenus.count { it.getCategory() == Category.MAIN } * 2_023
            }
            else -> 0
        }
    }
}