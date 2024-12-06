package christmas

import christmas.controller.Christmas
import christmas.model.BenefitManager
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    // 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
    Christmas(inputView = InputView(), outputView = OutputView(), benefitManager = BenefitManager()).start()
}
