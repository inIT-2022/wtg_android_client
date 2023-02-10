package ru.sectorsj.where_to_go.utils.format

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object FormatUtils {
    fun formatDate(date: String): String {
        val localDateTime = LocalDateTime.parse(date)
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd hh:mm")
            .format(localDateTime)
    }

    fun formatTime(date: String): String {
        val localDateTime = LocalDateTime.parse(date)
        return DateTimeFormatter
            .ofPattern("hh:mm")
            .format(localDateTime)
    }
}