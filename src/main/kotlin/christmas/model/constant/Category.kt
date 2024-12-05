package christmas.model.constant

import christmas.model.OrderedMenu

enum class Category(val menu: List<Pair<String, Int>>) {
    APPETIZER(listOf("양송이수프" to 6000, "타파스" to 5500, "시저샐러드" to 8000)),
    MAIN(listOf("티본스테이크" to 55000, "바비큐립" to 54000, "해산물파스타" to  35000 , "크리스마스파스타" to 25000)),
    DESSERT(listOf("초코케이크" to 15000, "아이스크림" to 5000)),
    BEVERAGE(listOf("제로콜라" to 3000, "레드와인" to 60000, "샴페인" to 25000));

//    fun getTotalPrice(orderedMenus: List<OrderedMenu>) {
//        val asd = entries.map { it.menu }.flatten()
//
//        asd.find { it.first ==  }
//    }
}
