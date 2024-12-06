package christmas.controller

import christmas.model.BenefitManager
import christmas.model.OrderedMenu
import christmas.model.constant.Badge
import christmas.model.constant.Category
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
        printReceipt(orderedMenus, visitDay)
    }

    private fun printReceipt(orderedMenus: List<OrderedMenu>, visitDay: Int) {
        outputView.printOrderMenu(orderedMenus)
        val orderedMenusBeforeAmount = orderedMenus.sumOf { it.getAmount() } // 총 금액
        outputView.printBeforeAmount(orderedMenusBeforeAmount)
        outputView.printPresentChampagne(orderedMenusBeforeAmount)
        val benefitInfo = canCheckDiscountAll(visitDay, orderedMenus, orderedMenusBeforeAmount) // 할인 혜택 내역
        outputView.printBenefitsInfo(benefitInfo)
        outputView.printAllBenefitsAmount(benefitInfo)
        val totalAmountAfterDiscount = calculateTotalAmountAfterDiscount(orderedMenusBeforeAmount, benefitInfo)
        outputView.printExpectPaymentAfterDiscount(totalAmountAfterDiscount)
        outputView.printEventBadge(eventBadge(benefitInfo))
    }

    private fun eventBadge(benefitInfo: List<Pair<String, Int>>): String {
        val totalBenefits = benefitInfo.sumOf { it.second }
        return when {
            totalBenefits >= Badge.SANTA.applyAmount -> Badge.SANTA.value
            totalBenefits >= Badge.TREE.applyAmount -> Badge.TREE.value
            totalBenefits >= Badge.STAR.applyAmount -> Badge.STAR.value
            else -> Badge.NONE.value
        }
    }

    private fun validateVisitDay(): Int = retryInput {
        val rawVisitDay = inputView.readVisitDay()
        val visitDay =
            requireNotNull(rawVisitDay.toIntOrNull()) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        require(visitDay in DECEMBER.startDay..DECEMBER.endDay) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
        visitDay
    }

    private fun validateMenuAndCount(): List<OrderedMenu> = retryInput {
        val rawMenuAndCount = inputView.readMenuAndCount().split(",")
        val orderedMenu = rawMenuAndCount.map { OrderedMenu.from(it.split("-")) }
        require(orderedMenu.all { it.getCategory() == Category.BEVERAGE  }.not() ) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
        require(orderedMenu.sumOf { it.quantity } < 20) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."}
        orderedMenu
    }


    private fun canCheckDiscountAll(visitDate: Int, orderedMenus: List<OrderedMenu>, orderedMenusBeforeAmount: Int): List<Pair<String,Int>> {
        if (orderedMenusBeforeAmount <= 10000) {
            return emptyList()
        }
        return benefitManager.discountAll(visitDate, orderedMenus)
    }

    private fun calculateTotalAmountAfterDiscount(beforeAmount: Int, benefitInfo: List<Pair<String, Int>>): Int {
        val benefitInfoRemovedChampagne = benefitInfo.filter { it.first != BenefitManager.PRESENT }
        val benefitAmount = benefitInfoRemovedChampagne.sumOf { it.second }
        return beforeAmount - benefitAmount
    }
}
