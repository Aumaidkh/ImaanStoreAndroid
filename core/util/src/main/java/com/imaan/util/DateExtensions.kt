package com.imaan.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toDayName():String {
    val dayFormat = SimpleDateFormat("EEEE",Locale.getDefault())
    return dayFormat.format(this)
}

fun Date.toMonthName(): String {
    val monthFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    return monthFormat.format(this)
}

fun Date.toDayOfMonth(): String {
    val calendar = java.util.Calendar.getInstance()
    calendar.time = this

    return calendar.get(java.util.Calendar.DAY_OF_MONTH).toString()
}