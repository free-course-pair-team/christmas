package christmas.view

import christmas.model.OrderedMenu
import christmas.model.constant.Month
import christmas.util.toWonFormat

class OutputView {
    fun printWelcomeMessage() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    fun printDayToEvent(day: Int) {
        println("${Month.DECEMBER.value}월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
    }

    fun printOrderMenu(orderedMenus: List<OrderedMenu>) {
        println("<주문 메뉴>")
        orderedMenus.forEach { println(it.toString()) }
        println()
    }

    fun printBeforeAmount(amount: Int) {
        println("<할인 전 총주문 금액>")
        println("${amount.toWonFormat()}\n")
    }

    fun printPresentChampagne(amount: Int) {
        println("<증정 메뉴>")
        if (amount >= 120_000) {
            println("샴페인 1개\n")
            return
        }
        println("없음\n")
    }

    fun printBenefitsInfo(discount: List<Pair<String,Int>>) {
        println("<혜택 내역>")
        if (discount.isEmpty()) {
            println("없음\n")
            return
        }
        discount.forEach {
            println("${it.first}: -${it.second.toWonFormat()}")
        }
        println()
    }

    fun printAllBenefitsAmount(discount: List<Pair<String,Int>>) {
        println("<총혜택 금액>")
        println("-${discount.sumOf { it.second }.toWonFormat()}\n")
    }

    fun expectPaymentAfterDiscount(amount: Int) {
        println("<할인 후 예상 결제 금액>")
        println(amount.toWonFormat())
    }
}
