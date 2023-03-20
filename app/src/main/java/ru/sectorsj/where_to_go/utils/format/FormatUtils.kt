package ru.sectorsj.where_to_go.utils.format

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object FormatUtils {
    private const val mscZone = "Europe/Moscow"

    fun formatDate(date: String): String {
        val moscowLocalDateTime = LocalDateTime.parse(date)
            .atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneId.of(mscZone))
            .toLocalDateTime()

        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")
            .format(moscowLocalDateTime)
    }

    fun formatTime(date: String): String {
        val localDateTime = LocalDateTime.parse(date)
        return DateTimeFormatter
            .ofPattern("HH:mm")
            .format(localDateTime)
    }
}