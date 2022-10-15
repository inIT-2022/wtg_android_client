package ru.sectorsj.where_to_go.utils.format

import java.lang.StringBuilder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object FormatUtils {
    fun formatDate(date: String): String {
        val sb = StringBuilder(date).append("Z")
        val instant = Instant.parse(sb)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault())
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd hh:mm")
            .format(localDateTime)
    }
}