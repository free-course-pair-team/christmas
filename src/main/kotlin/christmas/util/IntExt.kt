package christmas.util

import java.text.DecimalFormat

fun Int.toWonFormat(): String =
    DecimalFormat("#,###").format(this) + "원"
