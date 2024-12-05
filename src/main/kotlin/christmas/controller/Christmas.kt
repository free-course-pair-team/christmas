package christmas.controller

import christmas.model.OrderedMenu
import christmas.model.constant.Month.DECEMBER
import christmas.util.retryInput
import christmas.view.InputView
import christmas.view.OutputView

class Christmas(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        outputView.printWelcomeMessage()
        val visitDay = validateVisitDay()
        val orderedMenus = validateMenuAndCount()
        outputView.printDayToEvent(visitDay)
        outputView.printOrderMenu(orderedMenus)
        val orderedMenusBeforeAmount = orderedMenus.sumOf { it.getAmount() }
        outputView.printBeforeAmount(orderedMenusBeforeAmount)
        outputView.printPresentChampagne(orderedMenusBeforeAmount)
    }

    private fun validateVisitDay(): Int = retryInput {
        val rawVisitDay = inputView.readVisitDay()
        val visitDay = requireNotNull(rawVisitDay.toIntOrNull()) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
        require(visitDay in DECEMBER.startDay..DECEMBER.endDay) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
        visitDay
    }

    fun validateMenuAndCount(): List<OrderedMenu> = retryInput {
        val rawMenuAndCount = inputView.readMenuAndCount().split(",")
        rawMenuAndCount.map { OrderedMenu.from(it.split("-"))}
    }

}
