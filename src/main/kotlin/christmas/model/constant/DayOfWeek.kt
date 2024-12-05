package christmas.model.constant

enum class DayOfWeek() {
    FRIDAY,
    SATURDAY,
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY;

    companion object {
        fun sortedDayOfWeek(): List<DayOfWeek> {
            val days = DayOfWeek.entries.drop(5)+DayOfWeek.entries.take(5)
            println("days: $days")
            return days
        }
    }
}
