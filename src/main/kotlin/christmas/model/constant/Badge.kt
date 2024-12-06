package christmas.model.constant

enum class Badge(val value: String, val applyAmount: Int) {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0)
}
