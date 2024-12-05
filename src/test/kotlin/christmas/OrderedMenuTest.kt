package christmas

import christmas.model.OrderedMenu
import christmas.model.constant.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class OrderedMenuTest {

    @ParameterizedTest
    @ValueSource(
        strings = ["타파스-0", "쿨피스-1", "타파스0"]
    )
    @DisplayName("주문할 음식 하나 입력 시 예외 처리")
    fun validateOrderedMenu(input: String) {
        assertThrows<IllegalArgumentException> {
            OrderedMenu.from(input.split("-"))
        }
    }

    @ParameterizedTest
    @CsvSource(
        "'타파스-1', 5500"
    )
    @DisplayName("입력받은 주문한 음식의 금액 반환")
    fun getAmountOfOrderedMenu(input: String, output: Int) {
        val amount = OrderedMenu.from(input.split("-")).getAmount()
        assertEquals(output, amount)
    }

    @ParameterizedTest
    @CsvSource(
        "'타파스-1', 5500"
    )
    @DisplayName("입력한 음식의 카테고리 반환")
    fun test(input: String, output: Int) {
        val category = OrderedMenu.from(input.split("-")).getCategory()
        assertEquals(category, Category.APPETIZER)
    }
}