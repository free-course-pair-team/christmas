package christmas.model.constant

enum class DayOfWeek() {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    companion object {
        fun startDayIndex(): Int =  FRIDAY.ordinal
    }
}
