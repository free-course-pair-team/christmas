package christmas

class OutputView {
    fun printWelcomeMessage() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.`")

    fun printDayToEvent(day: Int) {
        println("12월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }
}
