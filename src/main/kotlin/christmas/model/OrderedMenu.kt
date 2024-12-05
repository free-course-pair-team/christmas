package christmas.model

import christmas.model.constant.Category

class OrderedMenu(val name: String, val quantity: Int) {

    fun getAmount(): Int {
        val menu =
            requireNotNull(Category.entries.map { it.menu }.flatten().find { it.first == name })
        return menu.second * quantity
    }

    fun getCategory(): Category =
        requireNotNull(Category.entries.find { it.menu.map { it.first }.contains(name) })


    override fun toString() =
        "$name ${quantity}개"

    companion object {
        fun from(inputMenu: List<String>): OrderedMenu {
            require(inputMenu.size == 2) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }

            val name = validateMenu(inputMenu[0])
            val quantity = validateQuantity(inputMenu[1])

            return OrderedMenu(name, quantity)
        }

        private fun validateQuantity(q: String): Int {
            val intQ = requireNotNull(q.toIntOrNull()) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
            require(intQ >= 1) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
            return intQ
        }

        private fun validateMenu(menu: String): String {
            val menuList =
                Category.entries.map { menuList -> menuList.menu.map { it.first } }.flatten()
            require(menuList.contains(menu)) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
            return menu
        }
    }
}
