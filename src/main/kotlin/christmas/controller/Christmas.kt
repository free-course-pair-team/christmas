package christmas.controller

import christmas.model.BenefitManager
import christmas.model.OrderedMenu
import christmas.model.constant.Month.DECEMBER
import christmas.util.retryInput
import christmas.view.InputView
import christmas.view.OutputView

class Christmas(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val benefitManager: BenefitManager,
) {
    fun start() {
        outputView.printWelcomeMessage()
        val visitDay = validateVisitDay() // 방문 일
        val orderedMenus = validateMenuAndCount() // 주문 메뉴
        outputView.printDayToEvent(visitDay)
        outputView.printOrderMenu(orderedMenus)
        val orderedMenusBeforeAmount = orderedMenus.sumOf { it.getAmount() } // 총 금액
        outputView.printBeforeAmount(orderedMenusBeforeAmount)
        outputView.printPresentChampagne(orderedMenusBeforeAmount)
        // 방문일과 메뉴, 총금액도?
        val benefitInfo = canCheckDiscountAll(visitDay, orderedMenus) // 할인 혜택 내역
        outputView.printBenefitsInfo(benefitInfo)
        outputView.printAllBenefitsAmount(benefitInfo)
        val totalAmountAfterDiscount = calculateTotalAmountAfterDiscount(orderedMenusBeforeAmount, benefitInfo)
        outputView.expectPaymentAfterDiscount(totalAmountAfterDiscount)
    }

    private fun validateVisitDay(): Int = retryInput {
        val rawVisitDay = inputView.readVisitDay()
        val visitDay =
            requireNotNull(rawVisitDay.toIntOrNull()) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        require(visitDay in DECEMBER.startDay..DECEMBER.endDay) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        visitDay
    }

    fun validateMenuAndCount(): List<OrderedMenu> = retryInput {
        val rawMenuAndCount = inputView.readMenuAndCount().split(",")
        rawMenuAndCount.map { OrderedMenu.from(it.split("-")) }
    }

    fun canCheckDiscountAll(visitDate: Int, orderedMenus: List<OrderedMenu>) =
        benefitManager.discountAll(visitDate, orderedMenus)

    fun calculateTotalAmountAfterDiscount(beforeAmount: Int, benefitInfo: List<Pair<String, Int>>): Int {
        val benefitInfoRemovedChampagne = benefitInfo.filter { it.first != BenefitManager.PRESENT }
        val benefitAmount = benefitInfoRemovedChampagne.sumOf { it.second }
        return beforeAmount - benefitAmount
    }


}
