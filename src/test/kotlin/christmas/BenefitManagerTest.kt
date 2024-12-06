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
        "7, 6069"
    )
    @DisplayName("주말, 주중 할인 확인")
    fun discountWeekdaysOrWeekends(input1: Int, output: Int) {
        val list = listOf(OrderedMenu("초코케이크", 2), OrderedMenu("아이스크림", 1) )
        val disCountAmount = BenefitManager().discountWeekdaysOrWeekends(input1, list)
        assertEquals(disCountAmount.second, output)
    }

    @ParameterizedTest
    @CsvSource(
        "3, 1000",
        "25, 1000",
        "26, 0"
    )
    @DisplayName("특별 할인 확인")
    fun discountSpecialDays(input1: Int, output: Int) {
        val disCountAmount = BenefitManager().discountSpecialDays(input1)
        assertEquals(disCountAmount.second, output)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 1000",
        "25, 3400",
    )
    @DisplayName("크리스마스 디데이 할인 확인")
    fun discountChristmasDay(input1: Int, output: Int) {
        val disCountAmount = BenefitManager().discountChristmasDay(input1)
        assertEquals(disCountAmount.second, output)
    }
}