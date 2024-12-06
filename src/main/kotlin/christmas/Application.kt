package christmas

import christmas.controller.Christmas
import christmas.model.BenefitManager
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    Christmas(inputView = InputView(), outputView = OutputView(), benefitManager = BenefitManager()).start()
}
