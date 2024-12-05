package christmas

import christmas.model.OrderedMenu
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderedMenu {

    @ParameterizedTest
    @ValueSource(
        strings = ["타파스-0,제로콜라-1", "쿨피스-1,제로콜라-1", "타파스0,제로콜라-1"]
    )
    @DisplayName("주문할 음식 입력 시 예외 처리")
    fun validateOrderedMenu(input: String) {
        assertThrows<IllegalArgumentException> {
            OrderedMenu.from(input.split(","))
        }
    }
}