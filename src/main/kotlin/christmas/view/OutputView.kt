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
        println("${amount.toWonFormat()}원")
    }

    fun printPresentChampagne(amount: Int) {
        println("<증정 메뉴>")
        if (amount >= 120_000) {
            println("샴페인 1개\n")
            return
        }
        println("없음\n")
    }

    fun printBenefitsDetail() {
        println("<혜택 내역>")
        println("크리스마스 디데이 할인: ")
        println("평일 할인: ")
        println("특별 할인: ")
        println("증정 이벤트: ")
    }
}
