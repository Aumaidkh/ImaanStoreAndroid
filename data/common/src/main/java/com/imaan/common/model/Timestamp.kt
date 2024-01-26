package com.imaan.common.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@JvmInline
value class Timestamp(val value: Long){
    fun formattedTimeStamp(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = Date(currentTimeMillis)

        val timestampDate = Date(this.value)

        val todayFormat = SimpleDateFormat("h a", Locale.getDefault())
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())

        return when {
            isSameDay(currentDate, timestampDate) -> "Today at ${todayFormat.format(timestampDate)}"
            isSameDay(Date(currentTimeMillis - 24 * 60 * 60 * 1000), timestampDate) -> "Yesterday at ${todayFormat.format(timestampDate)}"
            else -> {
                val dayOfWeek = dayOfWeekFormat.format(timestampDate)
                "On $dayOfWeek at ${todayFormat.format(timestampDate)}"
            }
        }
    }

    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val calendar1 = Calendar.getInstance()
        calendar1.time = date1

        val calendar2 = Calendar.getInstance()
        calendar2.time = date2

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
    }
}