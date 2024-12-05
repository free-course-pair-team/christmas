package christmas

import christmas.model.BenefitManager
import christmas.model.OrderedMenu
import christmas.model.constant.Category
import christmas.model.constant.DayOfWeek
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BenefitManagerTest {

    @ParameterizedTest
    @CsvSource(
        "9, 0",
        "8, 0",
        "7, 4046"
    )
    @DisplayName("주말, 주중 할인 확인")
    fun discountWeekdaysOrWeekends(input1: Int, output: Int) {
        val startDayOfWeek = DayOfWeek.FRIDAY

        val list = listOf(OrderedMenu("초코케이크", 1), OrderedMenu("아이스크림", 1) )
        val disCountAmount = BenefitManager().discountWeekdaysOrWeekends(input1, startDayOfWeek, list)
        assertEquals(disCountAmount, output)
    }
}