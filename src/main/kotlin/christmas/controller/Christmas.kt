package christmas

import christmas.model.Month.DECEMBER
import christmas.view.InputView
import christmas.view.OutputView

class Christmas(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        outputView.printWelcomeMessage()
        val visitDay = validateVisitDay()
        val (menu, menuCount) = inputView.readMenuAndCount()
    }

    fun validateVisitDay(): Int = retryInput {
        val rawVisitDay = inputView.readVisitDay()
        val visitDay = requireNotNull(rawVisitDay.toIntOrNull()) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
        require(visitDay in DECEMBER.startDay..DECEMBER.endDay) {"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
        visitDay
    }

//    fun validateMenuAndCount(): Pair<String, Int> = retryInput {
//        val rawMenuAndCount = inputView.readMenuAndCount().split(",")
//        val menuAndCount = rawMenuAndCount.map { OrderMenu(it.split("-")[0], it.split("-")[1].toInt())}
//        require(menuAndCount.size == 2) {"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."}
//        val menu = menuAndCount.first()
//        menu to
//    }

}
